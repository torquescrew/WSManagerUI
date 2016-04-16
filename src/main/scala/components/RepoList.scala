package components

import components.Entry.ReposList
import japgolly.scalajs.react.{BackendScope, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._

/**
  * Created by user on 10/04/16.
  */
object RepoList {

  case class Props(repos: ReposList)

  class Backend($: BackendScope[Props, Unit]) {

    def render(state: Unit, props: Props) = {
      val repoItems = props.repos.map((r) => {
        RepoItem(r("name"), r("path"))
      })

      <.div(repoItems, ^.className := "repoList")
    }
  }

  val component = ReactComponentB[Props]("RepoList")
    .renderBackend[Backend]
    .build

  def apply(repos: ReposList) = component(Props(repos))
}
