package oscillowars

class SquareRhom(radius: Double, center: Vector2, sharpness: Int)
	extends Rhombus(new Vector2(radius, radius), center, sharpness)

class SquareRect(radius: Double, center: Vector2, sharpness: Int)
	extends Rectangle(new Vector2(radius, radius), center, sharpness)