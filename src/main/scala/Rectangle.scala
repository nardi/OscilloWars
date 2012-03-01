package oscillowars

import scala.math._

class Rectangle(var radius: Vector2, var center: Vector2, var sharpness: Int) extends Shape
{	
	def tra(x: Double, terms: Int) = //(8/(Pi*Pi)) * (0 to (terms * 2)).filter(n => n % 2 == 1).map(n => ((sin(n*Pi/4) + sin(3*n*Pi/4))/(n*n)) * sin(n*x)).sum
	{
	  if (!Rectangle.traLookUpTable.keys.exists(t => t == terms))
			Rectangle.fillTable(terms)
	  val lookUpVal = round(((x*(Rectangle.lookUpResolution - 1))/(2*Pi)) % (Rectangle.lookUpResolution - 1)).toInt
	  val closestVal = Rectangle.traLookUpTable(terms)(lookUpVal)
	  closestVal
	} 
	  
	def x(t: Double) = center.x + radius.x * tra(2*Pi*t + Pi/2, sharpness)
	def y(t: Double) = center.y + radius.y * tra(2*Pi*t, sharpness)
}

object Rectangle
{
  val lookUpResolution = 1000
  var traLookUpTable: Map[Int, Array[Double]] = Map()
  def tra(x: Double, terms: Int) = (8/(Pi*Pi)) * (0 to (terms * 2)).filter(n => n % 2 == 1).map(n => ((sin(n*Pi/4) + sin(3*n*Pi/4))/(n*n)) * sin(n*x)).sum
  def fillTable(terms: Int)
  {
    val table: Array[Double] = Array.ofDim(lookUpResolution)
    table(0) = tra(0, terms)
    for (x <- 1 to (lookUpResolution - 1))
      table(x) = tra(x*2*Pi/(lookUpResolution - 1), terms)
    traLookUpTable = traLookUpTable + (terms -> table)
  }
}