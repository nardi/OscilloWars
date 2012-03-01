package oscillowars

import scala.math._

class Vector2(var x: Double, var y: Double)
{  
	def +(v2: Vector2) = new Vector2(x + v2.x, y + v2.y)
	
	def -(v2: Vector2) = new Vector2(x - v2.x, y - v2.y)
	
	def *(v2: Vector2) = new Vector2(x * v2.x, y * v2.y)
	def *(d: Double) = new Vector2(x * d, y * d)
	
	def /(v2: Vector2) = new Vector2(x / v2.x, y / v2.y)
	def /(d: Double) = new Vector2(x / d, y / d)
	
	def +=(v2: Vector2) = 
	{
	    x += v2.x
	    y += v2.y
	    this
	}
	
	def -=(v2: Vector2) =
	{
	    x -= v2.x
	    y -= v2.y
	    this
	}
	  
	def *=(v2: Vector2) =
	{
	    x *= v2.x
	    y *= v2.y
	    this
	}
	  
	def *=(d: Double) =
	{
	    x *= d
	    y *= d
	    this
	}
	  
	def /=(v2: Double) =
	{
	    x /= v2
	    y /= v2
	    this
	}
  
    def length = sqrt(x*x + y*y)
    def angle = atan2(y, x)
}