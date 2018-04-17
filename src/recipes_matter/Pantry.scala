package recipes_matter

import scala.collection.mutable.Map

object Pantry {
  
  var ingredients = collection.mutable.Map[String, String](("Apple", "5"))
  
  val convertables = collection.mutable.Map[String, Int](("sugar", 85), ("flour", 65))
  
//  def addIngredient(input: String, amount: Int) = {
//    if (ingredients.contains(input)) {
//      changeAmount(input, amount, "add")
//      println("The ingredient exists. Amount added to existing ingredient.")
//    } else ingredients(input) = amount
//  }
//  
//  def changeAmount(ingredient: String, amount: Int, way: String) = {
//    if (this.ingredients.contains(ingredient)) {
//      if (way == "reduce") {
//        if (ingredients(ingredient) - amount > 0) ingredients(ingredient) = ingredients(ingredient) - amount
//        else ingredients(ingredient) = 0
//      }
//      else ingredients(ingredient) = ingredients(ingredient) + amount
//    }
//  }

  def pantryInfo: String = {
    Reader.updatePantry
    val alku = "You have in your pantry:\n"
    val nothing = "Absolutely nothing."
    var jatko = ""
    if (ingredients.isEmpty) {
      jatko = nothing
    }
    else {
      for (osa <- ingredients) {
        jatko = jatko + osa._2 + " of " + osa._1
      }
    }
    return alku + jatko.toString
  }
  
  // Converts the amounts, returns an Array like ("5", "dl", "sugar")
  def converter(amount: String, name: String): Array[String] = {
    var amnt = amount.split(" ")
    var nro = amnt(0).toInt
    var dlkg = amnt(1).toString
    if (convertables.contains(name)) {
      if (dlkg == "dl") {
        nro = nro * convertables(name)
        dlkg = "g"
      } else if (dlkg == "g") {
        nro = nro / convertables(name)
        dlkg = "dl"
      }
    }
    Array(nro.toString, amnt.toString, name)
  }

  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}