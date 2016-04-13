package utils
import org.scalajs.dom.{Event, MessageEvent, WebSocket}

import scala.scalajs.js.typedarray.{ArrayBuffer => JSArrayBuffer}
import scala.scalajs.js
import scala.scalajs.js.{Dynamic, JSON}
import collection.mutable.ArrayBuffer

class SocketCallback(val path: String, val callback: (String) => Unit)

class Socket(val webSocket: WebSocket) {

  def send(data: JSArrayBuffer) = webSocket.send(data)
  def send(data: String) = webSocket.send(data)

  var callbacks = new ArrayBuffer[SocketCallback]

  def get(path: String, callback: (String) => Unit): Unit = {
    val obj = js.Dynamic.literal(path = path)

    callbacks += new SocketCallback(path, callback)

    print("sending ")
    println(JSON.stringify(obj))

    webSocket.send(JSON.stringify(obj))
  }

  webSocket.onmessage = (e: MessageEvent) => {
    val json = tryParseAsJson(e.data)

    if (json.isDefined) {
      //TODO: Make "path" more unique to avoid duplicates? What actually happens?
      val path = json.get.selectDynamic("path").toString.replace("\"", "")
      val data = json.get.selectDynamic("data").toString

      callbacks = callbacks.filter((sc) => {
        if (sc.path == path) {
          sc.callback(data.toString)
          false
        }
        else
          true
      })
    }
  }

  def tryParseAsJson(data: Any) = {
    try {
      Some(JSON.parse(data.toString))
    }
    catch {
      case _: Throwable => None
    }
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
