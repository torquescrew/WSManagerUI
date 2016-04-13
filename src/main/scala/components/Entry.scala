package components

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import org.scalajs.dom.{Event, WebSocket}
import org.scalajs.jquery.jQuery
import upickle.default._

import scala.scalajs.js
import utils.Socket

import scala.scalajs.js.JSON

/**
  * Created by user on 10/04/16.
  */
object Entry {

  case class State(repos: Seq[Map[String, String]])
  case class Props(webSocket: Socket)

  class Backend($: BackendScope[Props, State]) {

    def render(state: State, props: Props) = {
      println("render")

      state.repos.foreach((r) => {
        r.foreach((e) => println(e))

      })

      //TODO: RepoItem can't have multiple instances.
      val items = state.repos.map((r) => {
        RepoItem(r("name"), r("path"))
      })

      <.div(^.className := "main",
        items, Btn("clickme", Callback { println("i was clicked!") }))
    }

    def componentDidMount = Callback {
      println("componentDidMount")

      val ws = $.props.runNow().webSocket

      ws.get("repos", (result) => {

        print("We get a result: ")
        println(result)

        val res = read[Map[String, Seq[Map[String, String]]]](result)

        $.setState(State(res("repos"))).runNow()
      })

    }
  }

  val component = ReactComponentB[Props]("Entry")
    .initialState(State(Nil))
    .renderBackend[Backend]
    .componentDidMount(_.backend.componentDidMount)
    .build

  def apply(webSocket: Socket) = component(Props(webSocket))
}
