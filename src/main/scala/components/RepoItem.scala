package components
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB}

/**
  * Created by user on 10/04/16.
  */
object RepoItem {

//  case class State()
  case class Props(name: String, path: String)

  class Backend($: BackendScope[Props, Unit]) {

    def render(state: Unit, props: Props) = {
      <.div(^.className := "repo",
        <.div(props.name, ^.className := "repo-title"),
        <.div(props.path, ^.className := "repo-path"))
    }

  }

  val component = ReactComponentB[Props]("RepoItem")
    .renderBackend[Backend]
    .build

  def apply(name: String, path: String) = component(Props(name, path))
}
