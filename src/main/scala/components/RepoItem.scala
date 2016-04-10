package components
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB}

/**
  * Created by user on 10/04/16.
  */
object RepoItem {

//  case class State()
  case class Props(repoName: String, path: String)

  class Backend($: BackendScope[Props, Unit]) {

    def render(state: Unit, props: Props) = {
      <.div(^.className := "repoItem",
        <.div("Stable"),
        <.div("/home/user/Stable"))
    }

  }

  val component = ReactComponentB[Props]("RepoItem")
    .renderBackend[Backend]
    .build

  def apply(repoName: String, path: String) = component(Props(repoName, path))
}
