package example

import skinny.test.SkinnyFunSpec
import skinny.json.DefaultJSONStringOps

class AppSpec extends SkinnyFunSpec {
  addFilter(App, "/*")

  describe("Example app") {

    it("shows top page") {
      get("/") {
        status should equal(200)
      }
    }

    it("shows minified JSON") {
      post("/minify", """{"name"   :    "Alice"}""".getBytes) {
        status should equal(200)
        body should equal("""{"name":"Alice"}""".stripMargin)
        header("Content-Type") should equal("application/json; charset=UTF-8")
      }
    }

    it("shows prettified JSON") {
      post("/prettify", """{    "name": "Alice"}""".getBytes) {
        status should equal(200)
        body should equal("""{
           |  "name" : "Alice"
           |}""".stripMargin)
        header("Content-Type") should equal("application/json; charset=UTF-8")
      }
    }
  }

}
