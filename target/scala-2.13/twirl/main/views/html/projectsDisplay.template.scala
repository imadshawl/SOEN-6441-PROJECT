
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

object projectsDisplay extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[List[Project],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(result: List[Project], requestId: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.44*/("""

"""),format.raw/*3.1*/("""<ol>
    """),_display_(/*4.6*/for(index <- 0 until (if (result.length > 10) 10 else result.length)) yield /*4.75*/ {_display_(Seq[Any](format.raw/*4.77*/("""
    """),format.raw/*5.5*/("""<li>
        <p>
            """),_display_(/*7.14*/requestId),format.raw/*7.23*/("""
            """),format.raw/*8.13*/("""<a href='/owner?ownerId="""),_display_(/*8.38*/result/*8.44*/.get(index).ownerId),format.raw/*8.63*/("""'>"""),_display_(/*8.66*/result/*8.72*/.get(index).ownerId),format.raw/*8.91*/("""</a>
            """),_display_(/*9.14*/result/*9.20*/.get(index).dateSubmitted),format.raw/*9.45*/(""",
            <a href='https://www.freelancer.com/projects/"""),_display_(/*10.59*/result/*10.65*/.get(index).projectId),format.raw/*10.86*/("""'>"""),_display_(/*10.89*/result/*10.95*/.get(index).title),format.raw/*10.112*/("""</a>,
            Type:
            """),_display_(/*12.14*/result/*12.20*/.get(index).projectType),format.raw/*12.43*/(""",
            Skills:
            """),_display_(/*14.14*/for(skill <- result.get(index).skillsList) yield /*14.56*/ {_display_(Seq[Any](format.raw/*14.58*/("""
            """),format.raw/*15.13*/("""<a href='/wsSkills?skillId="""),_display_(/*15.41*/skill/*15.46*/.id),format.raw/*15.49*/("""&skillName="""),_display_(/*15.61*/skill/*15.66*/.name),format.raw/*15.71*/("""'>
                """),_display_(/*16.18*/skill/*16.23*/.name),format.raw/*16.28*/(""",
            </a>
            """)))}),format.raw/*18.14*/("""
            """),format.raw/*19.13*/("""<a href="#" onclick='document.getElementById("projectWordCount_"""),_display_(/*19.77*/(result.get(index).projectId)),format.raw/*19.106*/("""_"""),_display_(/*19.108*/requestId),format.raw/*19.117*/("""").submit();'>
                Word Stat
            </a>
            FKGL: """),_display_(/*22.20*/result/*22.26*/.get(index).readingEaseScores.fkgl),format.raw/*22.60*/(""",
            Flesch Reading Ease Index: """),_display_(/*23.41*/result/*23.47*/.get(index).readingEaseScores.fleschReadingEaseScore),format.raw/*23.99*/(""".
        <form id='projectWordCount_"""),_display_(/*24.37*/(result.get(index).projectId)),format.raw/*24.66*/("""_"""),_display_(/*24.68*/requestId),format.raw/*24.77*/("""' action='/wordStat' method='post'>
            <input type='hidden' name="wordCountMap" value='"""),_display_(/*25.62*/result/*25.68*/.get(index).wordStat),format.raw/*25.88*/("""' />
        </form>

    </li>
    """)))}),format.raw/*29.6*/("""
"""),format.raw/*30.1*/("""</ol>
"""))
      }
    }
  }

  def render(result:List[Project],requestId:String): play.twirl.api.HtmlFormat.Appendable = apply(result,requestId)

  def f:((List[Project],String) => play.twirl.api.HtmlFormat.Appendable) = (result,requestId) => apply(result,requestId)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/projectsDisplay.scala.html
                  HASH: 22067aff22df8fdf7a2b79647480da71244674ca
                  MATRIX: 931->1|1068->43|1098->47|1134->58|1218->127|1257->129|1289->135|1347->167|1376->176|1417->190|1468->215|1482->221|1521->240|1550->243|1564->249|1603->268|1648->287|1662->293|1707->318|1795->379|1810->385|1852->406|1882->409|1897->415|1936->432|2002->471|2017->477|2061->500|2125->537|2183->579|2223->581|2265->595|2320->623|2334->628|2358->631|2397->643|2411->648|2437->653|2485->674|2499->679|2525->684|2590->718|2632->732|2723->796|2774->825|2804->827|2835->836|2942->916|2957->922|3012->956|3082->999|3097->1005|3170->1057|3236->1096|3286->1125|3315->1127|3345->1136|3470->1234|3485->1240|3526->1260|3597->1301|3626->1303
                  LINES: 27->1|32->1|34->3|35->4|35->4|35->4|36->5|38->7|38->7|39->8|39->8|39->8|39->8|39->8|39->8|39->8|40->9|40->9|40->9|41->10|41->10|41->10|41->10|41->10|41->10|43->12|43->12|43->12|45->14|45->14|45->14|46->15|46->15|46->15|46->15|46->15|46->15|46->15|47->16|47->16|47->16|49->18|50->19|50->19|50->19|50->19|50->19|53->22|53->22|53->22|54->23|54->23|54->23|55->24|55->24|55->24|55->24|56->25|56->25|56->25|60->29|61->30
                  -- GENERATED --
              */
          