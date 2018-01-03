/*
package bookScalaImpatient.chapter10_operators

import scala.collection.mutable.ArrayBuffer

/**
  *
  */
object Exercise5_HtmlTableNice {


     class Table(val html: ArrayBuffer[String]) {
          private val st1 = "\n\t"
          private val st2 = "\n\t\t"
          private val st3 = "\n\t\t\t"
          html += "<table>"; html += st1; html += "<tr>"


          override def toString = html + st1 + "</tr>\n<table>"

          def |(elem: String): Table = {
               html += st2
               html +=
               Table(html + "\n\t\t<td>" + "\n\t\t\t" + elem + "\n\t\t</td>")
          }
          def ||(elem: String) = Table(html + "\n\t</tr>\n\t<tr>\n\t\t<td>" + "\n\t\t\t" + elem + "\n\t\t</td>")

          def ==(that: Table) = this.html == that.html
     }

     object Table {
          def apply(html: String = "<table>\n\t<tr>"): Table = new Table(html)
          def unapply(t: Table): Option[String] = Some(t.html)
     }


     def main(args: Array[String]) {

          val table: Table = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
          println(table)

/// note help todo woul dbe nice to be able to insert the spaces correctly at the right places with Traversals ... ?
          val checkTable: Table = Table("<table><tr><td>Java</td><td>Scala</td></tr><tr>" +
               "<td>Gosling</td><td>Odersky</td></tr><tr><td>JVM</td><td>JVM, .NET</td>"/*</tr><table>*/)
          println(checkTable)


          assert(checkTable == table)
     }
}
*/
