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
  type ReposList = Seq[RepoAttr]
  type RepoAttr = Map[String, String]

  case class State(repos: ReposList)
  case class Props(webSocket: Socket)

  class Backend($: BackendScope[Props, State]) {

    def render(state: State, props: Props) = {
      println("render")

      <.div(^.className := "main",
        RepoList(state.repos),
        Btn("New Branch", Callback { println("i was clicked!") }))
    }

    def componentDidMount = Callback {
      println("componentDidMount")

      val ws = $.props.runNow().webSocket

      ws.get("repos", (result) => {

        print("We get a result: ")
        println(result)

        val res = read[Map[String, ReposList]](result)

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
