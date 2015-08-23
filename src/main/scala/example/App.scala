package example

import skinny.json.DefaultJSONStringOps
import skinny.micro._
import skinny.micro.response._
import skinny.micro.scalate.ScalateSupport

object App extends AsyncWebApp with DefaultJSONStringOps with ScalateSupport {

  get("/") { implicit ctx =>
    contentType = "text/html"
    ssp("/index.ssp")
  }

  post("/minify") { implicit ctx =>
    contentType = "application/json"
    fromJSONStringToJValue(request.body, asIs = true) match {
      case Some(json) => Ok(toJSONStringAsIs(json))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

  post("/prettify") { implicit ctx =>
    contentType = "application/json"
    fromJSONStringToJValue(request.body, asIs = true) match {
      case Some(json) => Ok(toPrettyJSONStringAsIs(json))
      case _ => BadRequest(toJSONString(Map("error" -> "JSON parse error")))
    }
  }

}
