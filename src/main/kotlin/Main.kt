import controller.AppCotroller
import view.AppView

fun main() {
   val appView : AppView = AppView()
    val appController : AppCotroller = AppCotroller(appView)
    appController.selectOption()
    appController.OnExit()
}