import javax.servlet._
import skinny.micro._
import example.App

class Bootstrap extends LifeCycle {
  override def init(ctx: ServletContext) {
    App.mount(ctx)
  }
}
