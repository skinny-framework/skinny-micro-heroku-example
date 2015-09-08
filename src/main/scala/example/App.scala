package example

import skinny.micro._
import skinny.micro.contrib.{ JSONSupport, ScalateSupport }
import scala.util._

object App extends AsyncWebApp with JSONSupport with ScalateSupport {

  error {
    case e =>
      e.printStackTrace
      throw e
  }

  get("/") { implicit ctx =>
    contentType = "text/html"
    ssp("/index.ssp")
  }

  post("/minify") { implicit ctx =>
    contentType = "application/json"
    fromJSONString[Map[String, Any]](request.body) match {
      case Success(mapValue) => Ok(toJSONStringAsIs(mapValue))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

  post("/prettify") { implicit ctx =>
    contentType = "application/json"
    fromJSONString[Map[String, Any]](request.body) match {
      case Success(mapValue) => Ok(toPrettyJSONStringAsIs(mapValue))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

}
