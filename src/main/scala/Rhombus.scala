package oscillowars

import scala.math._

class Rhombus(var radius: Vector2, var center: Vector2, var sharpness: Int) extends Shape
{
	def tri(x: Double, terms: Int): Double =
	{
	  if (!Rhombus.triLookUpTable.keys.exists(t => t == terms))
			Rhombus.fillTable(terms)
	  val lookUpVal = round(((x*(Rhombus.lookUpResolution - 1))/(2*Pi)) % (Rhombus.lookUpResolution - 1)).toInt
	  val closestVal = Rhombus.triLookUpTable(terms)(lookUpVal)
	  closestVal
	}
	  
	def x(t: Double) = center.x + radius.x * tri(2*Pi*t + Pi/2, sharpness)
	def y(t: Double) = center.y + radius.y * tri(2*Pi*t, sharpness)
}

object Rhombus
{
  val lookUpResolution = 100
  var triLookUpTable: Map[Int, Array[Double]] = Map()
  def tri(x: Double, terms: Int) = (8/(Pi*Pi)) * (0 to (terms * 2)).filter(n => n % 2 == 1).map(n => (pow(-1, (n-1)/2)/(n*n)) * sin(n*x)).sum
  def fillTable(terms: Int)
  {
    val table: Array[Double] = Array.ofDim(lookUpResolution)
    table(0) = tri(0, terms)
    for (x <- 1 to (lookUpResolution - 1))
      table(x) = tri(x*2*Pi/(lookUpResolution - 1), terms)
    triLookUpTable = triLookUpTable + (terms -> table)
  }
}