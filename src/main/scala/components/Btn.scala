package components
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}

/**
  * Created by user on 10/04/16.
  */
object Btn {
  case class Props(text: String, callback: Callback)

  class Backend($: BackendScope[Props, Unit]) {

    def render(state: Unit, props: Props) = {
      <.div(props.text, ^.className := "myButton", ^.onClick --> props.callback)
    }
  }

  val component = ReactComponentB[Props]("Btn")
    .renderBackend[Backend]
    .build

  def apply(text: String, callback: Callback) = component(Props(text, callback))
}

