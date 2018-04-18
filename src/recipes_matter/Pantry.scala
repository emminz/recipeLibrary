package recipes_matter

import scala.collection.mutable.Map

object Pantry {
  
  var ingredients = collection.mutable.Map[String, String]()
  
  val convertables = collection.mutable.Map[String, String](("sugar", "85"), ("flour", "65"), ("olive oil", "90"))
  
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
    val alku = "You have in your pantry:\n\n"
    val nothing = "Absolutely nothing."
    var jatko = ""
    if (ingredients.isEmpty) {
      jatko = nothing
    }
    else {
      for (osa <- ingredients) {
        jatko = jatko + osa._2 + " of " + osa._1 + "\n"
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
      if (dlkg == "dl") {
        nro = nro.toDouble * convertables(name.toLowerCase).toDouble
        dlkg = "g"
      } else if (dlkg == "g") {
        nro = nro.toDouble / convertables(name.toLowerCase).toDouble
        dlkg = "dl"
      }
    }
    Array(nro.toString, dlkg.toString, name.toLowerCase)
  }

  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}