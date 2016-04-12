package components

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import org.scalajs.dom.{Event, WebSocket}
import org.scalajs.jquery.jQuery

import scala.scalajs.js
import utils.Socket

import scala.scalajs.js.JSON

/**
  * Created by user on 10/04/16.
  */
object Entry {

  case class Props(webSocket: Socket)

  class Backend($: BackendScope[Props, Unit]) {

    def render(state: Unit, props: Props) = {
      <.div(^.className := "main",
        RepoItem("Stable", "/home/user/Stable"), Btn("clickme", Callback { println("i was clicked!") }))
    }

    def componentDidMount = Callback {
      println("componentDidMount")

//      jQuery.getJSON("/repos", success = (data: Any) => {
//        println(data)
//      })
      val ws = $.props.runNow().webSocket

//      ws.send("message from entry")

//      val obj = js.Dynamic.literal(path = "repos")
//      println(JSON.stringify(obj))

      ws.get("repos", (result) => {
        print("We get a result: ")
        println(result)
      })

//      ws.onmessage = (e: Event) => {
//
//      }

    }
  }

  val component = ReactComponentB[Props]("Entry")
    .renderBackend[Backend]
    .componentDidMount(_.backend.componentDidMount)
    .build

  def apply(webSocket: Socket) = component(Props(webSocket))
}
