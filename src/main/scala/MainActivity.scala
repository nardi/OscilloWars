package oscillowars

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.content._
import _root_.android.widget._
import _root_.android.view._
import scala.math._
import oscillowars.Screen._

//The game
class MainActivity extends Activity with TypedActivity
{
  override def onCreate(bundle: Bundle)
  {
    super.onCreate(bundle)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                         WindowManager.LayoutParams.FLAG_FULLSCREEN)
    setContentView(R.layout.main)
    
    val d = getWindowManager().getDefaultDisplay()
    Screen(d.getWidth, d.getHeight) //for our coordinate conversion functions
    
    val main = findViewById(R.id.main_view).asInstanceOf[FrameLayout]
    
    val text = new TextView(this)
    text.setText("Score: ?") //wasn't useful after all
    main.addView(text)
    
    val thumbstick = new Thumbstick(new Vector2(-0.5 * Screen.whRatio, 0), 0.45 * Screen.whRatio, this)
    main.addView(thumbstick)
    
    val button = new Button(new Vector2(0.5 * Screen.whRatio, 0), 0.45 * Screen.whRatio, this)
    main.addView(button)
    
		//Input code. Can be used as multitouch reference (api 7)
    main.setOnTouchListener(new View.OnTouchListener
    {
      var stickPointerID = -1
      var buttonPointerID = -1
      
      def onTouch(v: View, e: MotionEvent): Boolean = 
      {
        val action = e.getAction() & MotionEvent.ACTION_MASK
        
				//match doesn't work?
        if (action == MotionEvent.ACTION_DOWN)
          newPointer(e.getPointerId(0), car(new Vector2(e.getX, e.getY)))
        else if (action == MotionEvent.ACTION_UP)
          pointerUp(e.getPointerId(0))
        else if (action == MotionEvent.ACTION_POINTER_DOWN)
          newPointer(e.getPointerId(1), car(new Vector2(e.getX(1), e.getY(1))))
        else if (action == MotionEvent.ACTION_POINTER_UP)
          pointerUp(e.getPointerId(1))
        
        for (index <- 0 until e.getPointerCount())
        {
          val id = e.getPointerId(index)
          val touchPoint = car(new Vector2(e.getX(index), e.getY(index)))
          
          if (id == stickPointerID)
            thumbstick.touch(touchPoint)
          else if (id == buttonPointerID)
            button.touch(touchPoint)
        }
        v.invalidate
        true
      }
        
      def newPointer(id: Int, touchPoint: Vector2)
      {
        if ((touchPoint - thumbstick.center).length <= thumbstick.radius)
          stickPointerID = id
        else if ((touchPoint - button.center).length <= button.radius)
          buttonPointerID = id
      }
        
      def pointerUp(id: Int)
      {
        if (id == stickPointerID)
        {
          thumbstick.reset
          stickPointerID = -1
        }
        else if (id == buttonPointerID)
        {
          button.reset
          buttonPointerID = -1
        }
      }
    })
    
    AudioInterface.initialize
    val actor = new AudioActor()
    val player = new Player(thumbstick)
		button.onTouch(player.shoot)
    actor.update(player.update)
    actor.start
  }
}
