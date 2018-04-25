package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.PrintWriter
import java.io._

object Reader {
  private val recipeFile = "recipe_library.txt"
  private val pantryFile = "pantry.txt"
  
  def recipeAdder(input: String) = {
    val split = input.split('#')
    val name = split(0)
    var method = split(1)
    val ingString = split(2)
    try {
      val rw = new FileWriter(new File(recipeFile), true)
      rw.write("\n#" + name + "\n")
      Search.knownRecipes += (name.toLowerCase -> Array(method, ingString))
      if (method.contains('\n')) method = method.replace('\n', '¤')
      rw.write('[' + method + '\n')
      rw.write('%' + ingString)
      rw.write('\n' + "-----")
      rw.flush()
      rw.close()
    } catch {
      case e: FileNotFoundException => println("Recipe library file is missing.")
      case e: IOException => println("Got an IOException!")
    } 
  } 
  
  def ingredientAdder(input: String, source: String) = {
    val split = input.trim.dropRight(1).split('¤')
    for (osa <- split) {
      val individuals = osa.split('§')
      var amount = {
        if (source == "recipe") "0"
        else if (individuals(0).trim.contains(' ')) individuals(0).trim.split(' ')(0)
        else individuals(0).trim.toString
      }
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
          if (source == "shop") {
            val conversion = Pantry.converter(amount, name)
            amount = conversion(0) + " " + conversion(1)
          }
          Pantry.ingredients.update(name, amount)
          pw.write("\n" + "# " + name + " ¤ " + amount)
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
    var allergen = ""
    try {
      val file = Source.fromFile(pantryFile)
      for (line <- file.getLines) {
        if (line.head == '#') {
          val parts = line.split('¤')
          val name = parts(0).drop(1).trim
          val amount = parts(1).split('&')(0).trim
          allergen = parts(1).split('&')(1).trim
          Pantry.ingredients.update(name, amount)
          if (allergen != "§") Pantry.allergens.update(name, allergen)
        }
      }
      file.close()
    } catch {
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Got an IOException!")
    }
  }
  
  // The method below updates the pantry.txt file to mirror the changes in Pantry.ingredients, thus saving the changes more permanently
  def updateFile = {
    try {
      var name = ""
      var amount = ""
      var allergen = ""
      val rw = new FileWriter(new File(pantryFile), false)
      rw.write("Known ingredients:\n& marks allergen")
      for (ingredient <- Pantry.ingredients) {
        name = ingredient._1
        amount = ingredient._2
        if (Pantry.allergens.contains(name)) allergen = Pantry.allergens(name)
        else allergen = "§"
        println("# " + name + " ¤ " + amount + " &" + allergen)
        rw.write("\n# " + name + " ¤ " + amount + " &" + allergen)
      }
      rw.flush()
      rw.close()
    } catch {
      case e: FileNotFoundException => println("Pantry file is missing.")
      case e: IOException => println("Got an IOException!")
    }
  }
  
  // checkAmount checks whether there is enough of an ingredient in the pantry. Returns true if enough, false if not enough.
  def checkAmount(amount: String, name: String): Boolean = {
    val nimi = name.toLowerCase.trim
    Reader.updatePantry
    var amnt = 0.0
    var compare = 0.0
    if (Pantry.ingredients.contains(nimi)) {
      if (amount.trim.contains(' ')) {
        amnt = amount.split(' ')(0).trim.toDouble
      } else amnt = amount.trim.toDouble
      if (Pantry.ingredients(nimi).trim.contains(' ')) {
        compare = Pantry.ingredients(nimi).split(' ')(0).trim.toDouble
      } else compare = Pantry.ingredients(nimi).trim.toDouble
      amnt <= compare
    } else false
  }
  
  def getSubIngredients(subIng: String): (String, String) = {
    val palat = subIng.trim.split('§')
    val ingName = palat(1).trim.toLowerCase
    var neededIngs = ""
    var neededMethods = ""
    if (Search.knownRecipes.contains(ingName.toLowerCase) && !checkAmount(palat(0), ingName)) {
      val otherIngredients = Search.knownRecipes(ingName.toLowerCase)(1)
      val subbies = getSubIngredients(otherIngredients)
      neededIngs += subbies._1
      neededMethods += Search.knownRecipes(ingName.toLowerCase)(0) + subbies._2
    } else {
      neededIngs += subIng + " ¤ "
    }
    (neededIngs, neededMethods)
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
        if (line.head == '#') {
          name = line.drop(1).trim
        }
        else if (line.head == '[') method = line.drop(1).trim
        else if (line.head == '%') {
          var ing = line.drop(1).trim.split('¤').toBuffer
          for (osa <- ing) {
            val stuff = getSubIngredients(osa)
            ingredients += stuff._1
            method += stuff._2
          }
          ingredients = ingredients.replaceAll("¤  ¤", "¤")
          ing = ingredients.trim.split('¤').toBuffer
          if (like != "") { // If like is defined, then the recipe is added only if it is looked for. If like is not filled, all recipes will do
            if (ingredients.contains(like)) {
              println("This recipe has the searched ingredient")
              for (osa <- ing) {
                val palat = osa.trim.split('§')
                if (palat.length == 2) {
                  if (!checkAmount(palat(0).trim, palat(1).trim)) missing += 1
                }
              }
              if (Search.N >= missing) suitables += (name -> Array(method, ingredients))
            }
          } else {
            for (osa <- ing) {
              val palat = osa.trim.split('§')
              if (!checkAmount(palat(0).trim, palat(1).trim)) missing += 1
            }
            if (Search.N >= missing) suitables += (name -> Array(method, ingredients))
          }
        } else if (line.head == '-') {
          Search.knownRecipes += (name.toString.toLowerCase -> Array(method.toString, ingredients.toString))
          missing = 0
          method = ""
          name = ""
          ingredients = ""
        }
      } // Now all the suitable recipes are in a Map ready to be dropped in the next stage
      if (avoid != "") { // If the ingredients contain an ingredient or allergen that should be avoided, the recipe will be dropped
        for (osa <- suitables) {
          for (pala <- osa._2) {
            if (pala.contains('§')) {
              if (pala.contains(avoid)) suitables -= osa._1
              else {
                val parts = pala.trim.split('¤')
                for (part <- parts) {
                  val indivs = part.trim.split('§')
                  val nimi = indivs(1).trim
                  val inAllergens = Pantry.allergens.contains(nimi)
                  if (inAllergens && Pantry.allergens(nimi) == avoid) suitables -= osa._1
                }
              }
            }
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
  
  def checkNumberInput(input: String) = {
    var works = false
    var nro = 0
    try {
      if (input.contains(' ')) {
        nro = input.split(' ')(0).trim.toInt
      } else nro = input.trim.toInt
      println(nro)
      if (nro > 0 && nro < 9999999) works = true
    } catch {
      case e: Exception => works = false
    }
    works
  }
}