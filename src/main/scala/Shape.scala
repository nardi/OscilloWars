package oscillowars

abstract class Shape
{
  def x(t: Double) : Double
  def y(t: Double) : Double
  
  var frequency : Vector2 = new Vector2(1, 1)
  
  def validate(t: Double)
  {
    if (t < 0 || t > 1)
      throw new IllegalArgumentException("Value of t must be between 0 and 1")
  }
  
  def apply(t: Double) =
  {
    validate(t)
    new Vector2(x(frequency.x * t), y(frequency.y * t))
  }
}