// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def wordStat(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "wordStat")
    }
  
    // @LINE:21
    def javascriptRoutes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "javascriptRoutes")
    }
  
    // @LINE:11
    def search(searchTerm:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "search" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("searchTerm", searchTerm)))))
    }
  
    // @LINE:8
    def skills(skillId:String, skillName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "skills" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("skillId", skillId)), Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("skillName", skillName)))))
    }
  
    // @LINE:15
    def ownerDetails(ownerId:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "owner" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("ownerId", ownerId)))))
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:9
  class ReverseWebSocketController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def wsSkills(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "wsSkills")
    }
  
    // @LINE:10
    def skillsWebSocket: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "skillsWebSocket")
    }
  
  }

  // @LINE:18
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
