package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.PrintWriter
import java.io._

class Reader {
  var mappi = Map[String, String]()
  
  val filename = "recipe_library.txt"
  val pw = new FileWriter(new File(filename), true)
//  try {
//      for (line <- Source.fromFile(filename).getLines) {
//          println(line)
//      }
//  } catch {
//      case e: FileNotFoundException => println("Couldn't find that file.")
//      case e: IOException => println("Got an IOException!")
//  }
  
  
  // TODO: make it not throw error when adding twice
  def recipeAdder(input: String) = {
    println(input)
    try {
      val pw = new FileWriter(new File(filename), true)
      pw.write(input)
      pw.flush()
      pw.close()
    } catch {
      case e: FileNotFoundException => println("Recipe library file is missing.")
      case e: IOException => println("Got an IOException!")
    }
  }
}