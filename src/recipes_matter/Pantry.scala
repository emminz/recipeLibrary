package recipes_matter

import scala.collection.mutable.Map

object Pantry {
  
  var ingredients = collection.mutable.Map[String, String]()
  var allergens = collection.mutable.Map[String, String]()
  val convertables = collection.mutable.Map[String, String](("sugar", "85"), ("flour", "65"), ("olive oil", "90"))
  
  def changeAmount(amount: String, ingredient: String, allergen: String, way: String): String = {
    var message = "I'm a placheolder hoping to never pop up."
    println("Changing!")
    println(way)
    if (this.ingredients.contains(ingredient)) {
      var changingAmount = amount
      val dlkg = {
        if (amount.contains(' ')) amount.split(' ')(1)
        else ""
      }
      val compare = {
        if (ingredients(ingredient).contains(' ')) ingredients(ingredient).split(' ')(1)
        else ""
      }
      if (dlkg != compare && !convertables.contains(ingredient)) message = "Oops, you're meant to measure " + ingredient + " in " + compare.toString
      else if (convertables.contains(ingredient)) {
        changingAmount = converter(amount, ingredient)(0) + " " + converter(amount, ingredient)(1)
//        val newNro = converter(amount, ingredient)(0)
//        val newDlkg = converter(amount, ingredient)(1)
        message = changeHelper(changingAmount, ingredient, way)
      } else {
          message = changeHelper(changingAmount, ingredient, way)
      }
    } else { // If the ingredient is not in the already-known ingredients, it must be a new one that will be added
      if (way == "reduce") message = "You can't reduce the amount of an ingredient that isn't in the pantry!"
      else {
        var input = amount + " § " + ingredient
        if (allergen != "") input += " § " + allergen
        input += " ¤ "
        Reader.ingredientAdder(input, "shop")
        message = "Ingredient added!"
      }
    }
    message
  }
  
  def changeHelper(amount: String, name: String, way: String): String = {
    var message = ""
    val amnt = {
      if (amount.trim.contains(' ')) amount.trim.split(' ')(0)
      else amount
    }
    val dlkg = {
      if (amount.trim.contains(' ')) " " + amount.trim.split(' ')(1)
      else ""
    }
    val compare = {
      if (ingredients(name).trim.contains(' ')) ingredients(name).trim.split(' ')(0)
      else ingredients(name).trim
    }
    if (way == "add") {
      val newAmount = compare.toDouble + amnt.toDouble
      println("compare: " + compare + " amnt: " + amnt)
      ingredients.update(name, newAmount.toString + dlkg)
      message = "Amount of " + name + " updated!"
    } else {
      if (compare >= amnt) { // If the amount in pantry is greater than that to be reduced, do the reduction
        val newAmount = compare.toDouble - amnt.toDouble
        ingredients.update(name, newAmount.toString + dlkg)
        message = "Amount of " + name + " updated!"
      } else message = "Cannot reduce! You only have " + ingredients(name) + " in your pantry."
    }
    println("updating file")
    Reader.updateFile
    message
  }

  def pantryInfo: String = {
    Reader.updatePantry
    val alku = "You have in your pantry:\n\n"
    val nothing = "Absolutely nothing."
    var jatko = ""
    if (ingredients.isEmpty) {
      jatko = nothing
    }
    else {
      for (osa <- ingredients) {
        val compare = {
          if (osa._2.contains(' ')) osa._2.split(' ')(0)
          else osa._2
        }
        if (compare.toDouble != 0.0) jatko = jatko + osa._2 + " of " + osa._1 + "\n"
      }
    }
    return alku + jatko.toString
  }
  
  // Converts the amounts, returns an Array like ("5", "dl", "sugar")
  def converter(amount: String, name: String): Array[String] = {
    var nro = 0.0
    var dlkg = ""
    if (amount.contains(" ")) {
      var amnt = amount.split(" ")
      nro = amnt(0).toDouble
      dlkg = amnt(1).toString
    } else if (amount.trim >= "0" && amount.trim <= "99999"){
      nro = amount.trim.toDouble
    }
    if (convertables.contains(name.toLowerCase)) {
      if (dlkg == "g") {
        nro = nro.toDouble / convertables(name.toLowerCase).toDouble
        dlkg = "dl"
      }
    }
    Array(nro.toString, dlkg.toString, name.toLowerCase)
  }

  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}