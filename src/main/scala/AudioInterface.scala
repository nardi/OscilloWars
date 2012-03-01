package oscillowars

import android.media._
import scala.math._

object AudioInterface
{
	val sampleRate = AudioTrack.getNativeOutputSampleRate(AudioManager.STREAM_MUSIC)
	val resolution = 16
	val channels = 2
	val bufferSize = (AudioTrack.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT)).toInt
	val buffer: Array[Short] = Array.ofDim(bufferSize)

	val audioOut = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM)
	
	def initialize()
	{
	  audioOut.play()
	}
	
	var currentPositionInBuffer = 0
	def sendFrame(frame: Array[Vector2])
	{
	  for (point <- frame)
	  {
			if (point.x > 1) point.x = 1
			if (point.x < -1) point.x = -1
			if (point.y > 1) point.y = 1
			if (point.y < -1) point.y = -1
		
	    buffer(currentPositionInBuffer) = (point.x * 32767).toShort
	    buffer(currentPositionInBuffer + 1) = (point.y * 32767).toShort
	    
	    currentPositionInBuffer = (currentPositionInBuffer + 2) % bufferSize
			if (currentPositionInBuffer < 2)
				audioOut.write(buffer, 0, bufferSize)
	  }
	}
}