
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

object wordStat extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Map[String, String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(wordCount: Map[String, String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""

"""),_display_(/*3.2*/main("Word Stat")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
    """),format.raw/*4.5*/("""<h1 align="center"> Word Stat </h1>
    <table align="center" border="solid">
        <tr>
            <th>
                Word
            </th>
            <th>
                Frequency
            </th>
        </tr>
        """),_display_(/*14.10*/for((word, frequency) <- wordCount) yield /*14.45*/ {_display_(Seq[Any](format.raw/*14.47*/("""
            """),format.raw/*15.13*/("""<tr>
                <td>
                    """),_display_(/*17.22*/word),format.raw/*17.26*/("""
                """),format.raw/*18.17*/("""</td>
                <td>
                    """),_display_(/*20.22*/frequency),format.raw/*20.31*/("""
                """),format.raw/*21.17*/("""</td>
            </tr>
        """)))}),format.raw/*23.10*/("""
    """),format.raw/*24.5*/("""</table>
""")))}))
      }
    }
  }

  def render(wordCount:Map[String, String]): play.twirl.api.HtmlFormat.Appendable = apply(wordCount)

  def f:((Map[String, String]) => play.twirl.api.HtmlFormat.Appendable) = (wordCount) => apply(wordCount)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/wordStat.scala.html
                  HASH: 5c90b41b269a34317c4878ebccfd17cfd06076bd
                  MATRIX: 923->1|1050->33|1080->38|1105->55|1144->57|1176->63|1444->304|1495->339|1535->341|1577->355|1653->404|1678->408|1724->426|1801->476|1831->485|1877->503|1943->538|1976->544
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|45->14|45->14|45->14|46->15|48->17|48->17|49->18|51->20|51->20|52->21|54->23|55->24
                  -- GENERATED --
              */
          