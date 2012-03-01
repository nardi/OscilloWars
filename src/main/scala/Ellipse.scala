package oscillowars

import scala.math._

class Ellipse(var radius: Vector2, var center: Vector2) extends Shape
{  
  def x(t: Double) = center.x + radius.x * cos(2*Pi*t)
  def y(t: Double) = center.y + radius.y * sin(2*Pi*t)
}