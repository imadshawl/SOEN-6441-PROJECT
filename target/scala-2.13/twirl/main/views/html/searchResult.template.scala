
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

object searchResult extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[SearchResults,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(result: SearchResults):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.25*/("""

"""),format.raw/*3.1*/("""<p>
    <b>
        Search terms:
    </b>
    """),_display_(/*7.6*/result/*7.12*/.searchTerm),format.raw/*7.23*/("""
    """),format.raw/*8.5*/("""<a href="#" onclick='document.getElementById("globalWordCount_".concat(""""),_display_(/*8.78*/result/*8.84*/.requestId),format.raw/*8.94*/("""")).submit();'>
        Global Stat
    </a>
    FKGL: """),_display_(/*11.12*/result/*11.18*/.readingEaseScores.fkgl),format.raw/*11.41*/(""",
    Flesch Reading Ease Index: """),_display_(/*12.33*/result/*12.39*/.readingEaseScores.fleschReadingEaseScore),format.raw/*12.80*/(""".
</p>

"""),_display_(/*15.2*/projectsDisplay(result.projectsList, result.requestId)),format.raw/*15.56*/("""

"""),format.raw/*17.1*/("""<form id='globalWordCount_"""),_display_(/*17.28*/result/*17.34*/.requestId),format.raw/*17.44*/("""' action='/wordStat' method='post'>
    <input type='hidden' name="wordCountMap" value='"""),_display_(/*18.54*/result/*18.60*/.wordStat),format.raw/*18.69*/("""' />
</form>"""))
      }
    }
  }

  def render(result:SearchResults): play.twirl.api.HtmlFormat.Appendable = apply(result)

  def f:((SearchResults) => play.twirl.api.HtmlFormat.Appendable) = (result) => apply(result)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/searchResult.scala.html
                  HASH: 659dc0748381569652dd74c5e5c88dc51ba4bebd
                  MATRIX: 921->1|1039->24|1069->28|1146->80|1160->86|1191->97|1223->103|1322->176|1336->182|1366->192|1452->251|1467->257|1511->280|1573->315|1588->321|1650->362|1688->374|1763->428|1794->432|1848->459|1863->465|1894->475|2011->565|2026->571|2056->580
                  LINES: 27->1|32->1|34->3|38->7|38->7|38->7|39->8|39->8|39->8|39->8|42->11|42->11|42->11|43->12|43->12|43->12|46->15|46->15|48->17|48->17|48->17|48->17|49->18|49->18|49->18
                  -- GENERATED --
              */
          