package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.PrintWriter
import java.io._
import recipes_matter.Pantry

class Reader {
  var mappi = Map[String, String]()
  
  val recipeFile = "recipe_library.txt"
  val pantryFile = "pantry.txt"
  
//  try {
//      for (line <- Source.fromFile(recipeFile).getLines) {
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
      val rw = new FileWriter(new File(recipeFile), true)
      rw.write('#' + name + '\n')
      rw.write('[' + method + ']' + '\n')
      rw.write('%' + ingString + '%' + '\n' + '\n')
      rw.flush()
      rw.close()
      println("success")
    } catch {
      case e: FileNotFoundException => println("Recipe library file is missing.")
      case e: IOException => println("Got an IOException!")
    } 
  } 
  
  def ingredientAdder(input: String) = {
    val split = input.trim.split('¤')
    for (osa <- split) {
      val individuals = osa.split('§')
      val amount = individuals(0)
      val name = individuals(1)
      val allergen = Option(individuals(2))
      try {
        val pw = new FileWriter(new File(pantryFile), true)
        pw.write('#' + name + " - " + amount)
        if (allergen.isDefined) pw.write("&" + allergen)
        pw.flush()
        pw.close()
        println("ingredients added!")
      } catch {
        case e: FileNotFoundException => println("Pantry file is missing.")
        case e: IOException => println("Got an IOException!")
      }
    }
  }
  
  def checkSmartInput(input: String) = {
    !input.contains('#') && !input.contains('[') && !input.contains(']') && 
    !input.contains('%') && !input.contains('§') && !input.contains('¤') && 
    !input.contains('^') && !input.contains('¨')
  }
}