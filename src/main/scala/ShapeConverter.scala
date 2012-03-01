package oscillowars

import scala.math._
import scala.collection.mutable._

object ShapeConverter
{
	val shapesToDraw: Queue[Shape] = Queue()
	val shapesToRotate: Queue[(Shape, Double, Vector2)] = Queue()
	var frameRate = 60.0
	
	def draw(shapes: Shape*)
	{
	  for (shape <- shapes)
			shapesToDraw += shape
	}

	def rotate(angle: Double, rotationPoint: Vector2, shapes: Shape*)
	{
	  for (shape <- shapes)
		  shapesToRotate += ((shape, angle, rotationPoint))
	}
	
	def draw(angle: Double, rotationPoint: Vector2, shapes: Shape*)
	{
	  for (shape <- shapes)
	  {
      rotate(angle, rotationPoint, shape)
      draw(shape)
	  }
	}
	
	def renderFrame()
	{
	  val pointsPerShape = round((AudioInterface.sampleRate / frameRate) / shapesToDraw.length).toInt
	  val pointsPerFrame = pointsPerShape * shapesToDraw.length
	  
	  val frame: Array[Vector2] = Array.ofDim(pointsPerFrame)
	  
	  for (shapeIndex <- 0 until shapesToDraw.length)
	  {
	    val shape = shapesToDraw(shapeIndex)
	    val startingPositionInFrame = shapeIndex * pointsPerShape
	    
	    for (pointIndex <- 0 until pointsPerShape)
	    {
	      val point = shape(pointIndex / pointsPerShape.toDouble)
	      
	      for (rotation <- shapesToRotate)
	      {
	        if (rotation._1 == shape)
	        {
	          val angle = rotation._2
	          val rotationPoint = rotation._3
	          
	          val relativePoint = point - rotationPoint
	          val radius = relativePoint.length
	          val a = atan2(relativePoint.y, relativePoint.x)
	          
	          val rotatedX = radius * cos(a + angle)
	          val rotatedY = radius * sin(a + angle)
	          
	          point.x = rotationPoint.x + rotatedX
	          point.y = rotationPoint.y + rotatedY
	        }
	      }
	      
	      frame(startingPositionInFrame + pointIndex) = point
	    }
	  }
	  
	  AudioInterface.sendFrame(frame)
	  shapesToRotate.clear()
	  shapesToDraw.clear()
	}
}