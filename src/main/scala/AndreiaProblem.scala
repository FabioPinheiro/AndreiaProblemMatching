import scala.collection.mutable.MutableList
import util.control.Breaks._
import java.io._
import java.nio.file.{ Files, FileSystems }

object AndreiaProblem extends App {
  println("AndreiaProblem")
  val outFileName = "out.txt"
  new File(outFileName).delete()
  println("createNewFile(" + outFileName + ")=" +  new File(outFileName).createNewFile())
  val defaultFS = FileSystems.getDefault()
  val separator = defaultFS.getSeparator()
  val file = List("column1.txt", "column2.txt")

  def printToFile(str: String) {
    val outFile = new File(outFileName)
    val outFileWriter = new FileWriter(outFile,true)
    try {
      outFileWriter.write(str + "\n")
    } finally { outFileWriter.close() }
  }

  def testFile(filename: String):Boolean = {
    val path = defaultFS.getPath(filename)
    val str = (s"The following ${if (Files.isDirectory(path)) "directory" else "file"} called $filename" +
      (if (Files.exists(path)) " exists." else " does not exists."))
    printToFile(str)
    return (Files.exists(path))
  }

  
  printToFile("AndreiaProblem:")
  
  file.foreach(p => if(!testFile(p)) System.exit(1) else print(p + " exists"))

  val lines1 = scala.io.Source.fromFile(file(0)).mkString.split("\n").toList
  val lines2 = scala.io.Source.fromFile(file(1)).mkString.split("\n").toList
  println(lines1)
  println(lines2)
  var count: Int = 0
  var valores1 = lines1.map { x: String => count += 1; ("Column:1 Line:" + count, x.toDouble, 'F', "TODO") }
  count = 0
  var valores2 = lines2.map { x: String => count += 1; ("Column:1 Line:" + count, x.toDouble, 'F', "TODO") }
  var valores = (valores1 ::: valores2).toArray

  for (aux1: Int <- 0 until valores.size) {
    breakable {
      val e1 = valores(aux1)
      if (e1._3 == 'F') {
        for (aux2: Int <- aux1 + 1 until valores.size) {
          val e2 = valores(aux2)
          if (e2._3 == 'F' && e2._2 == -e1._2) {
            valores(aux1) = (e1._1, e1._2, 'A', e2._1)
            valores(aux2) = (e2._1, e2._2, 'P', e1._1)
            break;
          }
        }
      }
    }
    println(aux1 + " " + valores(aux1))
  }

  var unmatched = valores.filter(p => p._3 == 'F')

  val data = valores ++ "$" ++ unmatched
  printToFile(">>>>valores")
  valores.foreach(p => printToFile(p.toString()))
  printToFile(">>>>unmatched")
  unmatched.foreach(p => printToFile(p.toString()))
  println("---------------")
  println(unmatched.deep.mkString("\n"))
}