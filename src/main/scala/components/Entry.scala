package components

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import org.scalajs.dom.{Event, WebSocket}
import org.scalajs.jquery.jQuery
import utils.Socket

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

      ws.send("message from entry")

      ws.onmessage = (e: Event) => {

      }

    }
  }

  val component = ReactComponentB[Props]("Entry")
    .renderBackend[Backend]
    .componentDidMount(_.backend.componentDidMount)
    .build

  def apply(webSocket: Socket) = component(Props(webSocket))
}
