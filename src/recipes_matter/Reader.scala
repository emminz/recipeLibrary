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
      rw.write('\n' + "-----" + '\n' + '#' + name + '\n')
      rw.write('[' + method + '\n')
      rw.write('%' + ingString)
      rw.flush()
      rw.close()
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
      if (individuals(0).trim.contains(' ')) {
        amount += " " + individuals(0).trim.split(' ')(1)
      }
      val name = individuals(1).trim
      var allergen = ""
      if (individuals.length == 3) {
        allergen = individuals(2).trim
      }
      try {
        updatePantry
        if (!Pantry.ingredients.contains(name)) {
          val pw = new FileWriter(new File(pantryFile), true)
          val stuff = Pantry.converter(amount, name)
          amount = stuff(0) + " " + stuff(1) 
          pw.write("\n" + "# " + name + " - " + amount)
          if (allergen != "") pw.write(" &" + allergen)
          else pw.write(" &§")
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
          //val allergen = parts(0).split('&')(1).trim
          Pantry.ingredients.update(name, amount)
        }
      }
      file.close()
    } catch {
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Got an IOException!")
    }
  }
  
  def checkAmount(amount: String, name: String): Boolean = {
    Reader.updatePantry
    var amnt = 0.0
    var compare = 0.0
    if (Pantry.ingredients.contains(name)) {
      if (amount.trim.contains(' ')) {
        amnt = amount.split(' ')(0).trim.toDouble
      } else amnt = amount.trim.toDouble
      if (Pantry.ingredients(name).trim.contains(' ')) {
        compare = Pantry.ingredients(name).split(' ')(0).trim.toDouble
      } else compare = Pantry.ingredients(name).trim.toDouble
      if (amnt >= compare) true
    }
    false
  }
  
  // The following method goes through the recipe library and seeks out all recipes that fulfill the search criteria.
  // Returns a Map like ("Banana split" -> ("Take a banana, do something with it", "1 § banana ¤ 100 g § icecream ¤")
  def readRecipes(like: String, avoid: String): collection.mutable.Map[String, Array[String]] = {
    var missing = 0
    try {
      val file = Source.fromFile(recipeFile)
      var suitables = collection.mutable.Map[String, Array[String]]()
      var name = ""
      var method = ""
      var ingredients = ""
      for (line <- file.getLines) {
        if (line.head == '#') name = line.drop(1)
        else if (line.head == '[') method = line.drop(1)
        else if (line.head == '%') {
          ingredients = line.drop(1)
          if (like != "") { // If like is defined, then the recipe is added only if it is looked for. If like is not filled, all recipes will do
            if (ingredients.contains(like)) {
              val ing = ingredients.trim.dropRight(1).split('¤')
              for (osa <- ing) {
                val palat = osa.trim.split('§')
                if (!checkAmount(palat(0).trim, palat(1).trim)) missing += 1
              }
              if (Search.N <= missing) suitables += (name -> Array(method, ingredients))
            }
          } else {
            suitables += (name -> Array(method, ingredients))
          }
        } // Now all the suitable recipes are in a Map ready to be dropped in the next stage
      } 
      if (avoid != "") { // If the ingredients contain an ingredient or allergen that should be avoided, the recipe will be dropped
        for (osa <- suitables) {
          if (osa._2.contains(avoid)) {
            suitables -= osa._1
          }
        }
      }
      suitables
    } catch {
      case e: FileNotFoundException => 
        println("Couldn't find that file.")
        collection.mutable.Map[String, Array[String]]("fail" -> Array("fail", "fail"))
      case e: IOException => println("Got an IOException!")
        collection.mutable.Map[String, Array[String]]("fail" -> Array("fail", "fail"))
    }
  }
  
  def checkSmartInput(input: String) = {
    !input.contains('#') && !input.contains('[') && !input.contains(']') && 
    !input.contains('%') && !input.contains('§') && !input.contains('¤') && 
    !input.contains('^') && !input.contains('¨')
  }
}