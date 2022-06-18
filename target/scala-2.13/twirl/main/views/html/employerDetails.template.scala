
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

object employerDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[OwnerDetailsResults,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(result: OwnerDetailsResults):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.31*/("""

"""),_display_(/*3.2*/main("Employer Details")/*3.26*/ {_display_(Seq[Any](format.raw/*3.28*/("""
    """),format.raw/*4.5*/("""<h1>Employer Details</h1>
    <ul>
        <li>
            ID: """),_display_(/*7.18*/result/*7.24*/.id),format.raw/*7.27*/("""
        """),format.raw/*8.9*/("""</li>
        <li>
            Username: """),_display_(/*10.24*/result/*10.30*/.username),format.raw/*10.39*/("""
        """),format.raw/*11.9*/("""</li>
        <li>
            Suspended: """),_display_(/*13.25*/result/*13.31*/.suspended),format.raw/*13.41*/("""
        """),format.raw/*14.9*/("""</li>
        <li>
            Closed: """),_display_(/*16.22*/result/*16.28*/.closed),format.raw/*16.35*/("""
        """),format.raw/*17.9*/("""</li>
        <li>
            Is Active: """),_display_(/*19.25*/result/*19.31*/.isActive),format.raw/*19.40*/("""
        """),format.raw/*20.9*/("""</li>
        <li>
            Force Verify: """),_display_(/*22.28*/result/*22.34*/.forceVerify),format.raw/*22.46*/("""
        """),format.raw/*23.9*/("""</li>
        <li>
            Avatar: """),_display_(/*25.22*/result/*25.28*/.avatar),format.raw/*25.35*/("""
        """),format.raw/*26.9*/("""</li>
        <li>
            Email: """),_display_(/*28.21*/result/*28.27*/.email),format.raw/*28.33*/("""
        """),format.raw/*29.9*/("""</li>
        <li>
            Reputation: """),_display_(/*31.26*/result/*31.32*/.reputation),format.raw/*31.43*/("""
        """),format.raw/*32.9*/("""</li>
        <li>
            Profile Description: """),_display_(/*34.35*/result/*34.41*/.profileDescription),format.raw/*34.60*/("""
        """),format.raw/*35.9*/("""</li>
        <li>
            Display Name: """),_display_(/*37.28*/result/*37.34*/.displayName),format.raw/*37.46*/("""
        """),format.raw/*38.9*/("""</li>
        <li>
            Registration Date: """),_display_(/*40.33*/result/*40.39*/.registrationDate),format.raw/*40.56*/("""
        """),format.raw/*41.9*/("""</li>
        <li>
            Location: """),_display_(/*43.24*/result/*43.30*/.location),format.raw/*43.39*/("""
        """),format.raw/*44.9*/("""</li>
        <li>
            Role: """),_display_(/*46.20*/result/*46.26*/.role),format.raw/*46.31*/("""
        """),format.raw/*47.9*/("""</li>
        <li>
            First Name: """),_display_(/*49.26*/result/*49.32*/.firstName),format.raw/*49.42*/("""
        """),format.raw/*50.9*/("""</li>
        <li>
            Last Name: """),_display_(/*52.25*/result/*52.31*/.lastName),format.raw/*52.40*/("""
        """),format.raw/*53.9*/("""</li>
        <li>
            Primary Currency Name: """),_display_(/*55.37*/result/*55.43*/.primaryCurrencyName),format.raw/*55.63*/("""
        """),format.raw/*56.9*/("""</li>
        <li>
            Status:
            <ul>
                <li>
                    Payment Verified: """),_display_(/*61.40*/result/*61.46*/.status.paymentVerified),format.raw/*61.69*/("""
                """),format.raw/*62.17*/("""</li>
                <li>
                    Email Verified: """),_display_(/*64.38*/result/*64.44*/.status.emailVerified),format.raw/*64.65*/("""
                """),format.raw/*65.17*/("""</li>
                <li>
                    Deposit Made: """),_display_(/*67.36*/result/*67.42*/.status.depositMade),format.raw/*67.61*/("""
                """),format.raw/*68.17*/("""</li>
                <li>
                    Profile Complete: """),_display_(/*70.40*/result/*70.46*/.status.profileComplete),format.raw/*70.69*/("""
                """),format.raw/*71.17*/("""</li>
                <li>
                    Phone Verified: """),_display_(/*73.38*/result/*73.44*/.status.phoneVerified),format.raw/*73.65*/("""
                """),format.raw/*74.17*/("""</li>
                <li>
                    Identity Verified: """),_display_(/*76.41*/result/*76.47*/.status.identityVerified),format.raw/*76.71*/("""
                """),format.raw/*77.17*/("""</li>
                <li>
                    Facebook Connected: """),_display_(/*79.42*/result/*79.48*/.status.facebookConnected),format.raw/*79.73*/("""
                """),format.raw/*80.17*/("""</li>
                <li>
                    Freelancer VerifiedUser: """),_display_(/*82.47*/result/*82.53*/.status.freelancerVerifiedUser),format.raw/*82.83*/("""
                """),format.raw/*83.17*/("""</li>
                <li>
                    Linkedin Connected: """),_display_(/*85.42*/result/*85.48*/.status.linkedinConnected),format.raw/*85.73*/("""
                """),format.raw/*86.17*/("""</li>
            </ul>
        </li>
    </ul>
    """),_display_(/*90.6*/projectsDisplay(result.projectsList, result.id)),format.raw/*90.53*/("""
""")))}))
      }
    }
  }

  def render(result:OwnerDetailsResults): play.twirl.api.HtmlFormat.Appendable = apply(result)

  def f:((OwnerDetailsResults) => play.twirl.api.HtmlFormat.Appendable) = (result) => apply(result)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/employerDetails.scala.html
                  HASH: 75b975155500431abe781705cc71bfff023bbb5d
                  MATRIX: 930->1|1054->30|1084->35|1116->59|1155->61|1187->67|1281->135|1295->141|1318->144|1354->154|1425->198|1440->204|1470->213|1507->223|1579->268|1594->274|1625->284|1662->294|1731->336|1746->342|1774->349|1811->359|1883->404|1898->410|1928->419|1965->429|2040->477|2055->483|2088->495|2125->505|2194->547|2209->553|2237->560|2274->570|2342->611|2357->617|2384->623|2421->633|2494->679|2509->685|2541->696|2578->706|2660->761|2675->767|2715->786|2752->796|2827->844|2842->850|2875->862|2912->872|2992->925|3007->931|3045->948|3082->958|3153->1002|3168->1008|3198->1017|3235->1027|3302->1067|3317->1073|3343->1078|3380->1088|3453->1134|3468->1140|3499->1150|3536->1160|3608->1205|3623->1211|3653->1220|3690->1230|3774->1287|3789->1293|3830->1313|3867->1323|4015->1444|4030->1450|4074->1473|4120->1491|4213->1557|4228->1563|4270->1584|4316->1602|4407->1666|4422->1672|4462->1691|4508->1709|4603->1777|4618->1783|4662->1806|4708->1824|4801->1890|4816->1896|4858->1917|4904->1935|5000->2004|5015->2010|5060->2034|5106->2052|5203->2122|5218->2128|5264->2153|5310->2171|5412->2246|5427->2252|5478->2282|5524->2300|5621->2370|5636->2376|5682->2401|5728->2419|5811->2476|5879->2523
                  LINES: 27->1|32->1|34->3|34->3|34->3|35->4|38->7|38->7|38->7|39->8|41->10|41->10|41->10|42->11|44->13|44->13|44->13|45->14|47->16|47->16|47->16|48->17|50->19|50->19|50->19|51->20|53->22|53->22|53->22|54->23|56->25|56->25|56->25|57->26|59->28|59->28|59->28|60->29|62->31|62->31|62->31|63->32|65->34|65->34|65->34|66->35|68->37|68->37|68->37|69->38|71->40|71->40|71->40|72->41|74->43|74->43|74->43|75->44|77->46|77->46|77->46|78->47|80->49|80->49|80->49|81->50|83->52|83->52|83->52|84->53|86->55|86->55|86->55|87->56|92->61|92->61|92->61|93->62|95->64|95->64|95->64|96->65|98->67|98->67|98->67|99->68|101->70|101->70|101->70|102->71|104->73|104->73|104->73|105->74|107->76|107->76|107->76|108->77|110->79|110->79|110->79|111->80|113->82|113->82|113->82|114->83|116->85|116->85|116->85|117->86|121->90|121->90
                  -- GENERATED --
              */
          