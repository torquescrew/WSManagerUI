package utils
import org.scalajs.dom.{Event, WebSocket}

import scala.scalajs.js.typedarray.ArrayBuffer


class Socket(webSocket: WebSocket) {

  def send(data: ArrayBuffer) = webSocket.send(data)
  def send(data: String) = webSocket.send(data)
  var onmessage = webSocket.onmessage

  def get(path: String, callback: (String) => Unit): Unit = {
    
  }

}



/**
  * Created by user on 10/04/16.
  */
object SocketMaker {

  def apply(callback: (Socket) => Unit): Unit = {
    val ws = new WebSocket("ws://localhost:4567/echo")

    ws.onopen = (e: Event) => {
      val socket = new Socket(ws)
      println("web socket opened!")
      ws.send("hi there")
      callback(socket)
    }

    ws.onmessage = (e: Event) => {
      println("todo")
    }
  }

}
