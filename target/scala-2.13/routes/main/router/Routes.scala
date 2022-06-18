// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:9
  WebSocketController_1: controllers.WebSocketController,
  // @LINE:18
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:9
    WebSocketController_1: controllers.WebSocketController,
    // @LINE:18
    Assets_2: controllers.Assets
  ) = this(errorHandler, HomeController_0, WebSocketController_1, Assets_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, WebSocketController_1, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """skills""", """controllers.HomeController.skills(skillId:String, skillName:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """wsSkills""", """controllers.WebSocketController.wsSkills(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """skillsWebSocket""", """controllers.WebSocketController.skillsWebSocket"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """search""", """controllers.HomeController.search(searchTerm:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """wordStat""", """controllers.HomeController.wordStat(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """owner""", """controllers.HomeController.ownerDetails(ownerId:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """javascriptRoutes""", """controllers.HomeController.javascriptRoutes(request:Request)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_skills1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("skills")))
  )
  private[this] lazy val controllers_HomeController_skills1_invoker = createInvoker(
    HomeController_0.skills(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "skills",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """skills""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_WebSocketController_wsSkills2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("wsSkills")))
  )
  private[this] lazy val controllers_WebSocketController_wsSkills2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      WebSocketController_1.wsSkills(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "wsSkills",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """wsSkills""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_WebSocketController_skillsWebSocket3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("skillsWebSocket")))
  )
  private[this] lazy val controllers_WebSocketController_skillsWebSocket3_invoker = createInvoker(
    WebSocketController_1.skillsWebSocket,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebSocketController",
      "skillsWebSocket",
      Nil,
      "GET",
      this.prefix + """skillsWebSocket""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_search4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("search")))
  )
  private[this] lazy val controllers_HomeController_search4_invoker = createInvoker(
    HomeController_0.search(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "search",
      Seq(classOf[String]),
      "GET",
      this.prefix + """search""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_HomeController_wordStat5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("wordStat")))
  )
  private[this] lazy val controllers_HomeController_wordStat5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.wordStat(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "wordStat",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """wordStat""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_HomeController_ownerDetails6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("owner")))
  )
  private[this] lazy val controllers_HomeController_ownerDetails6_invoker = createInvoker(
    HomeController_0.ownerDetails(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "ownerDetails",
      Seq(classOf[String]),
      "GET",
      this.prefix + """owner""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Assets_versioned7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned7_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_HomeController_javascriptRoutes8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("javascriptRoutes")))
  )
  private[this] lazy val controllers_HomeController_javascriptRoutes8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.javascriptRoutes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "javascriptRoutes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """javascriptRoutes""",
      """ Javascript routing""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index())
      }
  
    // @LINE:8
    case controllers_HomeController_skills1_route(params@_) =>
      call(params.fromQuery[String]("skillId", None), params.fromQuery[String]("skillName", None)) { (skillId, skillName) =>
        controllers_HomeController_skills1_invoker.call(HomeController_0.skills(skillId, skillName))
      }
  
    // @LINE:9
    case controllers_WebSocketController_wsSkills2_route(params@_) =>
      call { 
        controllers_WebSocketController_wsSkills2_invoker.call(
          req => WebSocketController_1.wsSkills(req))
      }
  
    // @LINE:10
    case controllers_WebSocketController_skillsWebSocket3_route(params@_) =>
      call { 
        controllers_WebSocketController_skillsWebSocket3_invoker.call(WebSocketController_1.skillsWebSocket)
      }
  
    // @LINE:11
    case controllers_HomeController_search4_route(params@_) =>
      call(params.fromQuery[String]("searchTerm", None)) { (searchTerm) =>
        controllers_HomeController_search4_invoker.call(HomeController_0.search(searchTerm))
      }
  
    // @LINE:13
    case controllers_HomeController_wordStat5_route(params@_) =>
      call { 
        controllers_HomeController_wordStat5_invoker.call(
          req => HomeController_0.wordStat(req))
      }
  
    // @LINE:15
    case controllers_HomeController_ownerDetails6_route(params@_) =>
      call(params.fromQuery[String]("ownerId", None)) { (ownerId) =>
        controllers_HomeController_ownerDetails6_invoker.call(HomeController_0.ownerDetails(ownerId))
      }
  
    // @LINE:18
    case controllers_Assets_versioned7_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned7_invoker.call(Assets_2.versioned(path, file))
      }
  
    // @LINE:21
    case controllers_HomeController_javascriptRoutes8_route(params@_) =>
      call { 
        controllers_HomeController_javascriptRoutes8_invoker.call(
          req => HomeController_0.javascriptRoutes(req))
      }
  }
}
