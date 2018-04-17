package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.PrintWriter
import java.io._

object Reader {
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
    val split = input.trim.dropRight(1)split('¤')
    for (osa <- split) {
      val individuals = osa.split('§')
      var amount = "0"
      val name = individuals(1).trim
      var allergen = ""
      println(osa)
      if (individuals.length == 3) {
        allergen = individuals(2).trim
      }
      try {
        updatePantry
        if (!Pantry.ingredients.contains(name)) {
          val pw = new FileWriter(new File(pantryFile), true)
          println("Reader sends: " + amount + " and name " + name)
          amount = Pantry.converter(amount, name)(0) + " " + Pantry.converter(amount, name)(1) 
          pw.write("\n" + "# " + name + " - " + amount)
          if (allergen != "") pw.write("&" + allergen)
          else pw.write("&§")
          pw.flush()
          pw.close()
        }
      } catch {
        case e: FileNotFoundException => println("Pantry file is missing.")
        case e: IOException => println("Got an IOException!")
      }
    }
  }
  
  // The function below informs Pantry what the text file knows so that the info can be accessed easily
  def updatePantry = {
    try {
      val file = Source.fromFile(pantryFile)
      for (line <- file.getLines) {
        if (line.head == '#') {
          val parts = line.split('-')
          val name = parts(0).drop(1).trim
          val amount = parts(1).split('&')(0).trim
          println(line)
          //val allergen = parts(0).split('&')(1).trim
          Pantry.ingredients.update(name, amount)
        }
      }
      println(Pantry.ingredients)
      file.close()
    } catch {
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Got an IOException!")
    }
  }
  
  def updatePantryFile(name: String, amount: String) = {
    
  }
  
  def checkSmartInput(input: String) = {
    !input.contains('#') && !input.contains('[') && !input.contains(']') && 
    !input.contains('%') && !input.contains('§') && !input.contains('¤') && 
    !input.contains('^') && !input.contains('¨')
  }
}