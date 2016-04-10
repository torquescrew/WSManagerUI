import components.{Entry, RepoItem}
import japgolly.scalajs.react.ReactDOM
import org.scalajs.dom
import org.scalajs.dom.{Event, WebSocket, document}
import utils.SocketMaker
import utils.Socket

import scala.scalajs.js.JSApp

object JSMain extends JSApp {

  def main(): Unit = {
    SocketMaker((socket: Socket) => {

      ReactDOM.render(Entry(socket), document.getElementById("app"))

    })
  }

}

