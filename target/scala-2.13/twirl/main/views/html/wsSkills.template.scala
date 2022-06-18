
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

object wsSkills extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[SkillsSearchResult,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(result: SkillsSearchResult, url: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.43*/("""

"""),_display_(/*3.2*/main("Skills")/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""
"""),format.raw/*4.1*/("""<script>
    var webSocket;
    function init() """),format.raw/*6.21*/("""{"""),format.raw/*6.22*/("""
        """),format.raw/*7.9*/("""initWebSocket();
    """),format.raw/*8.5*/("""}"""),format.raw/*8.6*/("""

    """),format.raw/*10.5*/("""function initWebSocket() """),format.raw/*10.30*/("""{"""),format.raw/*10.31*/("""
        """),format.raw/*11.9*/("""writeToScreen("Initiating websocket");
        webSocket = new WebSocket(""""),_display_(/*12.37*/url),format.raw/*12.40*/("""");
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;
        webSocket.onmessage = onMessage;
        webSocket.onerror = onError;
    """),format.raw/*17.5*/("""}"""),format.raw/*17.6*/("""

    """),format.raw/*19.5*/("""function onOpen(evt) """),format.raw/*19.26*/("""{"""),format.raw/*19.27*/("""
        """),format.raw/*20.9*/("""writeToScreen("CONNECTED");
    """),format.raw/*21.5*/("""}"""),format.raw/*21.6*/("""

    """),format.raw/*23.5*/("""function onClose(evt) """),format.raw/*23.27*/("""{"""),format.raw/*23.28*/("""
        """),format.raw/*24.9*/("""writeToScreen("DISCONNECTED");
    """),format.raw/*25.5*/("""}"""),format.raw/*25.6*/("""

    """),format.raw/*27.5*/("""function onError(evt) """),format.raw/*27.27*/("""{"""),format.raw/*27.28*/("""
        """),format.raw/*28.9*/("""writeToScreen("ERROR: " + evt.data);
        writeToScreen("ERROR: " + JSON.stringify(evt));
    """),format.raw/*30.5*/("""}"""),format.raw/*30.6*/("""

    """),format.raw/*32.5*/("""function writeToScreen(message) """),format.raw/*32.37*/("""{"""),format.raw/*32.38*/("""
        """),format.raw/*33.9*/("""console.log("New message: ", message);
    """),format.raw/*34.5*/("""}"""),format.raw/*34.6*/("""

    """),format.raw/*36.5*/("""function onMessage(evt) """),format.raw/*36.29*/("""{"""),format.raw/*36.30*/("""
        """),format.raw/*37.9*/("""var receivedData = JSON.parse(evt.data);
        console.log("New Data: ", receivedData);
        $("#skillFkgl").html(receivedData.readingEaseScores.fkgl);
        $("#skillFlesh").html(receivedData.readingEaseScores.fleschReadingEaseScore);
        $("#skillProjects").html(receivedData.renderedProjectHtml);
    """),format.raw/*42.5*/("""}"""),format.raw/*42.6*/("""

    """),format.raw/*44.5*/("""window.addEventListener("load", init, false);
</script>
<p>
    <b>
        Searched skill:
    </b>
    """),_display_(/*50.6*/result/*50.12*/.skill.name),format.raw/*50.23*/("""
    """),format.raw/*51.5*/("""<a href="#" onclick='document.getElementById("globalWordCount_".concat(""""),_display_(/*51.78*/result/*51.84*/.requestId),format.raw/*51.94*/("""")).submit();'>
        Global Stat
    </a>

    FKGL: <div id="skillFkgl">"""),_display_(/*55.32*/result/*55.38*/.readingEaseScores.fkgl),format.raw/*55.61*/("""</div>,
    Flesch Reading Ease Index: <div id="skillFlesh">"""),_display_(/*56.54*/result/*56.60*/.readingEaseScores.fleschReadingEaseScore),format.raw/*56.101*/("""</div>.
</p>
<div id="skillProjects">
    """),_display_(/*59.6*/projectsDisplay(result.projectsList, result.requestId)),format.raw/*59.60*/("""
"""),format.raw/*60.1*/("""</div>

<div id="skillWordCount">
    <form id='globalWordCount_"""),_display_(/*63.32*/result/*63.38*/.requestId),format.raw/*63.48*/("""' action='/wordStat' method='post'>
        <input type='hidden' name="wordCountMap" value='"""),_display_(/*64.58*/result/*64.64*/.wordStat),format.raw/*64.73*/("""' />
    </form>
</div>

""")))}))
      }
    }
  }

  def render(result:SkillsSearchResult,url:String): play.twirl.api.HtmlFormat.Appendable = apply(result,url)

  def f:((SkillsSearchResult,String) => play.twirl.api.HtmlFormat.Appendable) = (result,url) => apply(result,url)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/wsSkills.scala.html
                  HASH: 54def00c6a1e3042511d47f0b546f06cf5a6eb9e
                  MATRIX: 929->1|1065->42|1095->47|1117->61|1156->63|1184->65|1261->115|1289->116|1325->126|1373->148|1400->149|1435->157|1488->182|1517->183|1554->193|1657->269|1681->272|1871->435|1899->436|1934->444|1983->465|2012->466|2049->476|2109->509|2137->510|2172->518|2222->540|2251->541|2288->551|2351->587|2379->588|2414->596|2464->618|2493->619|2530->629|2656->728|2684->729|2719->737|2779->769|2808->770|2845->780|2916->824|2944->825|2979->833|3031->857|3060->858|3097->868|3444->1188|3472->1189|3507->1197|3645->1309|3660->1315|3692->1326|3725->1332|3825->1405|3840->1411|3871->1421|3979->1502|3994->1508|4038->1531|4127->1593|4142->1599|4205->1640|4277->1686|4352->1740|4381->1742|4476->1810|4491->1816|4522->1826|4643->1920|4658->1926|4688->1935
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|37->6|37->6|38->7|39->8|39->8|41->10|41->10|41->10|42->11|43->12|43->12|48->17|48->17|50->19|50->19|50->19|51->20|52->21|52->21|54->23|54->23|54->23|55->24|56->25|56->25|58->27|58->27|58->27|59->28|61->30|61->30|63->32|63->32|63->32|64->33|65->34|65->34|67->36|67->36|67->36|68->37|73->42|73->42|75->44|81->50|81->50|81->50|82->51|82->51|82->51|82->51|86->55|86->55|86->55|87->56|87->56|87->56|90->59|90->59|91->60|94->63|94->63|94->63|95->64|95->64|95->64
                  -- GENERATED --
              */
          