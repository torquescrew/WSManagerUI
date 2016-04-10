package components

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}

/**
  * Created by user on 10/04/16.
  */
object Entry {

  class Backend($: BackendScope[Unit, Unit]) {

    def render(state: Unit, props: Unit) = {
      <.div(^.className := "main",
        RepoItem("Stable", "/home/user/Stable"))
    }

    def componentDidMount = Callback {
      println("componentDidMount")
    }
  }

  val component = ReactComponentB[Unit]("Entry")
    .renderBackend[Backend]
    .componentDidMount(_.backend.componentDidMount)
    .buildU

  def apply() = component()
}
