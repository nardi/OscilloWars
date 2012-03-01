package oscillowars

class Line(var p1: Vector2, var p2: Vector2) extends Shape
{
    def x(t: Double) : Double = p1.x + (p2.x - p1.x) * t
    def y(t: Double) : Double = p1.y + (p2.y - p1.y) * t
}