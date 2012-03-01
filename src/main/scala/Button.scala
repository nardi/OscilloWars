package oscillowars

import _root_.android.content._
import _root_.android.graphics._
import _root_.android.view._
import oscillowars.Screen._

//Thing that can be pressed
class Button(var center: Vector2, var radius: Double, context: Context) extends View(context)
{
  private var onTouchList: List[Button => Unit] = List()
  def onTouch(function: Button => Unit) { onTouchList = function :: onTouchList }
  private var onReleaseList: List[Button => Unit] = List()
  def onRelease(function: Button => Unit) { onReleaseList = function :: onReleaseList }
  
  private val painter = new Paint(Paint.ANTI_ALIAS_FLAG)
  var pressed = false
  
  def touch(point: Vector2)
  {
    if (!pressed)
    {
      pressed = true
      onTouchList.foreach(f => f(this))
    }
  }
  
  def reset()
  {
    if (pressed)
    {
      pressed = false
      onReleaseList.foreach(f => f(this))
    }
  }
  
  override def onDraw(canvas: Canvas)
  {
	  super.onDraw(canvas)
	  painter.setColor(0xFFFFFFFF)
	  canvas.drawCircle(pixX(center.x).toFloat, pixY(center.y).toFloat, pixD(radius).toFloat, painter)
	  if (!pressed)
    {
      painter.setColor(0xFF000000)
      canvas.drawCircle(pixX(center.x).toFloat, pixY(center.y).toFloat, pixD(radius - 0.05).toFloat, painter)
    }
  }
}