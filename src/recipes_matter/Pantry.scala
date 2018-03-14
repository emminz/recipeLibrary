package recipes_matter

import scala.collection.mutable.Map

class Pantry {
  
  var ingredients = collection.mutable.Map[String, Int]()
  
  def addIngredient(input: String, amount: Int) = {
    if (ingredients.contains(input)) {
      changeAmount(input, amount, "add")
      println("The ingredient exists. Amount added to existing ingredient.")
    } else ingredients(input) = amount
  }
  
  def changeAmount(ingredient: String, amount: Int, way: String) = {
    if (this.ingredients.contains(ingredient)) {
      if (way == "reduce") {
        if (ingredients(ingredient) - amount > 0) ingredients(ingredient) = ingredients(ingredient) - amount
        else ingredients(ingredient) = 0
      }
      else ingredients(ingredient) = ingredients(ingredient) + amount
    }
  }
  
  def pantryInfo: String = {
    val alku = "You have in your pantry:\n\n"
    val nothing = "Abslutely nothing."
    println(alku)
    var jatko = ""
    if (ingredients.isEmpty) {
      jatko = nothing
    }
    else {
      for (osa <- ingredients) {
        jatko = jatko + osa._1 + " of " + osa._2
      }
    }
    println(jatko).toString
  }
  
  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}