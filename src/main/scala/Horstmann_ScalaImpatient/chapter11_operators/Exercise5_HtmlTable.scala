package Horstmann_ScalaImpatient.chapter11_operators

/**
  *
  */
object Exercise5_HtmlTable {


     class Table(val html: String = "<table><tr>") {
          override def toString = html + "</tr><table>"

          def |(elem: String) = Table(html + "<td>" + elem + "</td>")
          def ||(elem: String) = Table(html + "</tr><tr><td>" + elem + "</td>")

          def ==(that: Table) = this.html == that.html
     }

     object Table {
          def apply(html: String = "<table><tr>"): Table = new Table(html)
          def unapply(t: Table): Option[String] = Some(t.html)
     }


     def main(args: Array[String]) {

          val table: Table = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
          println(table)

          val checkTable: Table = Table("<table><tr><td>Java</td><td>Scala</td></tr><tr>" +
               "<td>Gosling</td><td>Odersky</td></tr><tr><td>JVM</td><td>JVM, .NET</td>"/*</tr><table>*/)
          println(checkTable)


          assert(checkTable == table)
     }
}
