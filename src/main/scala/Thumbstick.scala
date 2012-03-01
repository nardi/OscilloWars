package oscillowars

import _root_.android.content._
import _root_.android.graphics._
import _root_.android.view._
import scala.math._
import oscillowars.Screen._

//Thing that can be dragged around
class Thumbstick(var center: Vector2, var radius: Double, context: Context) extends View(context)
{    
  private val painter = new Paint(Paint.ANTI_ALIAS_FLAG)
  var stickPos = new Vector2(0,0)
  
  override def onAttachedToWindow()
  {
	  super.onAttachedToWindow()
  }
  
  def touch(point: Vector2)
  {
    val relPoint = point - center
    val relLength = min(relPoint.length, radius) / radius //limit distance
    stickPos = (relPoint / relPoint.length) * relLength
  }
  
  def reset()
  {
    stickPos = new Vector2(0,0)
  }
  
  override def onDraw(canvas: Canvas)
  {
	  super.onDraw(canvas)
		
		//outer circle
	  painter.setColor(0xFFFFFFFF)
	  canvas.drawCircle(pixX(center.x).toFloat, pixY(center.y).toFloat, pixD(radius).toFloat, painter)
	  painter.setColor(0xFF000000)
	  canvas.drawCircle(pixX(center.x).toFloat, pixY(center.y).toFloat, pixD(radius - 0.05).toFloat, painter)
		
		//inner circle
	  painter.setColor(0xFFFFFFFF)
	  canvas.drawCircle(pixX(center.x + (stickPos.x * 0.6 * radius)).toFloat, pixY(center.y + (stickPos.y * 0.6 * radius)).toFloat, pixD(0.4*radius).toFloat, painter)
  }
}