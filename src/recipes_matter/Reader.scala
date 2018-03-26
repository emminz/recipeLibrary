package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.PrintWriter
import java.io._

class Reader {
  var mappi = Map[String, String]()
  
  val filename = "recipe_library.txt"
  val pw = new PrintWriter(new File(filename))
//  try {
//      for (line <- Source.fromFile(filename).getLines) {
//          println(line)
//      }
//  } catch {
//      case e: FileNotFoundException => println("Couldn't find that file.")
//      case e: IOException => println("Got an IOException!")
//  }
  
  def recipeAdder(input: String) = {
    println(input)
    try {
      pw.write(input)
    } catch {
      case e: FileNotFoundException => println("Recipe library file is missing.")
      case e: IOException => println("Got an IOException!")
    } finally {
      pw.close()
    }

  }
}