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
 
  
  //TODO: sanitize input
  def recipeAdder(input: String) = {
    val split = input.split('#')
    val name = split(0)
    val method = split(1)
    val ingString = split(2)
    try {
      val pw = new FileWriter(new File(filename), true)
      pw.write('#' + name + '\n')
      pw.write('[' + method + ']' + '\n')
      pw.write('%' + ingString + '%' + '\n' + '\n')
      pw.flush()
      pw.close()
      println("success")
    } catch {
      case e: FileNotFoundException => println("Recipe library file is missing.")
      case e: IOException => println("Got an IOException!")
    } 
  } 
  
  def checkSmartInput(input: String) = {
    !input.contains('#') && !input.contains('[') && !input.contains(']') && 
    !input.contains('%') && !input.contains('§') && !input.contains('¤') && 
    !input.contains('^') && !input.contains('¨')
  }
}