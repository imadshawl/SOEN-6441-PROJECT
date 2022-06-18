
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

object skills extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[SkillsSearchResult,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(result: SkillsSearchResult):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.30*/("""

"""),_display_(/*3.2*/main("Skills")/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""
    """),format.raw/*4.5*/("""<p>
        <b>
            Searched skill:
        </b>
        """),_display_(/*8.10*/result/*8.16*/.skill.name),format.raw/*8.27*/("""
        """),format.raw/*9.9*/("""<a href="#" onclick='document.getElementById("globalWordCount_".concat(""""),_display_(/*9.82*/result/*9.88*/.requestId),format.raw/*9.98*/("""")).submit();'>
            Global Stat
        </a>
        FKGL: """),_display_(/*12.16*/result/*12.22*/.readingEaseScores.fkgl),format.raw/*12.45*/(""",
        Flesch Reading Ease Index: """),_display_(/*13.37*/result/*13.43*/.readingEaseScores.fleschReadingEaseScore),format.raw/*13.84*/(""".
    </p>

    """),_display_(/*16.6*/projectsDisplay(result.projectsList, result.requestId)),format.raw/*16.60*/("""

    """),format.raw/*18.5*/("""<form id='globalWordCount_"""),_display_(/*18.32*/result/*18.38*/.requestId),format.raw/*18.48*/("""' action='/wordStat' method='post'>
        <input type='hidden' name="wordCountMap" value='"""),_display_(/*19.58*/result/*19.64*/.wordStat),format.raw/*19.73*/("""' />
    </form>
""")))}))
      }
    }
  }

  def render(result:SkillsSearchResult): play.twirl.api.HtmlFormat.Appendable = apply(result)

  def f:((SkillsSearchResult) => play.twirl.api.HtmlFormat.Appendable) = (result) => apply(result)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/skills.scala.html
                  HASH: b63a54dc43bdf86f09f7f59c085f36fbb85ca9c7
                  MATRIX: 920->1|1043->29|1073->34|1095->48|1134->50|1166->56|1262->126|1276->132|1307->143|1343->153|1442->226|1456->232|1486->242|1584->313|1599->319|1643->342|1709->381|1724->387|1786->428|1832->448|1907->502|1942->510|1996->537|2011->543|2042->553|2163->647|2178->653|2208->662
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|39->8|39->8|39->8|40->9|40->9|40->9|40->9|43->12|43->12|43->12|44->13|44->13|44->13|47->16|47->16|49->18|49->18|49->18|49->18|50->19|50->19|50->19
                  -- GENERATED --
              */
          