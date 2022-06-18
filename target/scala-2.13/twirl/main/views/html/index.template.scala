
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Freelancelot")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""
    """),format.raw/*4.5*/("""<div class="main-container">
        <div class="title-section">
            <h1>Welcome to Freelancelot</h1>
        </div>
        <div class="input-section">
            <div class="search-widget">
                <input type="text" placeholder="Enter search terms" name="searchText" id="searchText"/>
                <button type="submit" id="searchButton" name="searchButton" onclick="search()"><i class="fa fa-search" ></i></button>
            </div>
        </div>
        <div id="content">
            <div class="result" id="result0">
            </div>
            <div class="result" id="result1">
            </div>
            <div class="result" id="result2">
            </div>
            <div class="result" id="result3">
            </div>
            <div class="result" id="result4">
            </div>
            <div class="result" id="result5">
            </div>
            <div class="result" id="result6">
            </div>
            <div class="result" id="result7">
            </div>
            <div class="result" id="result8">
            </div>
            <div class="result" id="result9">
            </div>
        </div>
    </div>
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 774b7cb72d21bb16e335787606f3a0f989ec18b7
                  MATRIX: 900->1|996->4|1023->6|1051->26|1090->28|1121->33
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4
                  -- GENERATED --
              */
          