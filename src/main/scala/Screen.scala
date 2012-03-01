package oscillowars

import scala.math._

//Converts between pixel (screenWidth by screenHeight, from top-left) and cartesian (1 by x, from center) coordinates
object Screen
{
  var width = 0
  var height = 0
  var whRatio = 0.0
  var hwRatio = 0.0
	
  def apply(w: Double, h: Double)
  {
    if (h < w)
    {
      whRatio = w/h
      hwRatio = 1
    }
    else
    {
      whRatio = 1
      hwRatio = h/w
    }
    width = w.toInt
    height = h.toInt
  }
  
  def carX(pixX: Double) =  2 * ((pixX/width) - 0.5) * whRatio
  def carY(pixY: Double) = -2 * ((pixY/height) - 0.5) * hwRatio
  
  def pixX(carX: Double) =  ((carX/(2 * whRatio)) + 0.5) * width
  def pixY(carY: Double) = ((-carY/(2 * hwRatio)) + 0.5) * height
  
  def car(pix: Vector2) = new Vector2(carX(pix.x), carY(pix.y))
  def pix(car: Vector2) = new Vector2(pixX(car.x), pixY(car.y))
  
  def pixD(carD: Double) = 0.5 * carD * min(width, height)
  def carD(pixD: Double) = 2 * pixD / min(width, height)
}