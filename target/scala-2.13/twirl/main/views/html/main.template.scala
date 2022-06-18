
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <title>"""),_display_(/*6.17*/title),format.raw/*6.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*7.54*/routes/*7.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*7.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*8.59*/routes/*8.65*/.Assets.versioned("images/favicon.png")),format.raw/*8.104*/("""">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </head>
    <body>
        """),format.raw/*14.32*/("""
        """),_display_(/*15.10*/content),format.raw/*15.17*/("""

        """),format.raw/*17.9*/("""<script src=""""),_display_(/*17.23*/routes/*17.29*/.Assets.versioned("javascripts/main.js")),format.raw/*17.69*/("""" type="text/javascript"></script>
        <script type="text/javascript" src=""""),_display_(/*18.46*/routes/*18.52*/.HomeController.javascriptRoutes()),format.raw/*18.86*/(""""></script>
    </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: ac65a7e680244346b1ab583ffa48cbe427310049
                  MATRIX: 911->1|1035->32|1062->33|1148->93|1173->98|1261->160|1275->166|1337->207|1424->268|1438->274|1498->313|1778->655|1815->665|1843->672|1880->682|1921->696|1936->702|1997->742|2104->822|2119->828|2174->862
                  LINES: 27->1|32->2|33->3|36->6|36->6|37->7|37->7|37->7|38->8|38->8|38->8|43->14|44->15|44->15|46->17|46->17|46->17|46->17|47->18|47->18|47->18
                  -- GENERATED --
              */
          