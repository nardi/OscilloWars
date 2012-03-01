package oscillowars

import scala.math._

//The 'player character'
class Player(var thumbstick: Thumbstick)
{
  var position = new Vector2(0,0)
  var angle = 0.0
  val circle = new Circle(0.03, position)
  val rhombus = new Rhombus(new Vector2(0.1, 0.15), new Vector2(0,0), 10)
  
	def shoot(button: Button)
	{
		//shooting stuff
	}
	
  def update(t: Double)
  {
    position += (thumbstick.stickPos * 0.01)
    if (thumbstick.stickPos.length > 0)
      angle = thumbstick.stickPos.angle - Pi/2
		
    if (position.x > 1) position.x = 1
    if (position.x < -1) position.x = -1
    if (position.y > 1) position.y = 1
    if (position.y < -1) position.y = -1
		
    val rotatedPos = new Vector2(position.x + (0.005*cos(2*t)), position.y + (0.005*sin(t))) //makes it float around
    circle.center = rotatedPos
    rhombus.center = rotatedPos
    ShapeConverter.draw(angle, rotatedPos, circle, rhombus)
  }
}