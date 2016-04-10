import components.{Entry, RepoItem}
import japgolly.scalajs.react.ReactDOM
import org.scalajs.dom
import org.scalajs.dom.{Event, WebSocket, document}

import scala.scalajs.js.JSApp

object JSMain extends JSApp {

  def setupWebSocket(): Unit = {
    val ws = new WebSocket("ws://localhost:4567/echo")

    ws.onopen = (e: Event) => {
      println("web socket opened!")
    }

    ws.onmessage = (e: Event) => {
      println("todo")
    }
  }

  def main(): Unit = {
    setupWebSocket()


    ReactDOM.render(Entry(), document.getElementById("app"))
  }


}

