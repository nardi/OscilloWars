package oscillowars

import scala.actors.Actor
import scala.actors.Actor._

//handles the audio stuff
class AudioActor extends Actor
{
  private var updateList: List[Double => Unit] = List()
  def update(function: Double => Unit) { updateList = function :: updateList }
  def act()
  {
    var t = 0.0
    while (true)
    {
      t += 0.05
      updateList.foreach(f => f(t))
      ShapeConverter.renderFrame
    }
  }
}