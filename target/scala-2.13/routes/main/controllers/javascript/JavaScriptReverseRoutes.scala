// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def wordStat: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.wordStat",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "wordStat"})
        }
      """
    )
  
    // @LINE:21
    def javascriptRoutes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.javascriptRoutes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "javascriptRoutes"})
        }
      """
    )
  
    // @LINE:11
    def search: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.search",
      """
        function(searchTerm0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "search" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("searchTerm", searchTerm0)])})
        }
      """
    )
  
    // @LINE:8
    def skills: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.skills",
      """
        function(skillId0,skillName1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "skills" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("skillId", skillId0), (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("skillName", skillName1)])})
        }
      """
    )
  
    // @LINE:15
    def ownerDetails: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.ownerDetails",
      """
        function(ownerId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "owner" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("ownerId", ownerId0)])})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseWebSocketController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def wsSkills: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.wsSkills",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "wsSkills"})
        }
      """
    )
  
    // @LINE:10
    def skillsWebSocket: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.WebSocketController.skillsWebSocket",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "skillsWebSocket"})
        }
      """
    )
  
  }

  // @LINE:18
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
