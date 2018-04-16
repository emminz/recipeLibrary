package recipes_matter

import scala.collection.mutable.Map
import recipes_matter.Reader

class Pantry {
  
  var ingredients = collection.mutable.Map[String, Int](("Apple", 5))
  
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
    val alku = "You have in your pantry:\n"
    val nothing = "Abslutely nothing."
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
  
  def checkConvert(amount: String, name: String) = {
    if (name == "sugar" || name == "flour") converter(amount, name)
  }
  
  def converter(amount: String, name: String) = {
    var amnt = amount.split(" ")
    var nro = amnt(0).toInt
    var dlkg = amnt(1).toString
    if (name == "sugar") {
      if (dlkg == "dl") {
        nro = nro * 85
        dlkg = "g"
      } else if (dlkg == "g")  {
        nro = nro / 85
        dlkg = "dl"
      } else "Please measure sugar in grams or desiliters"
    } else if (name == "flour") {
      if (amnt(1) == "dl") {
        nro = nro * 65
        dlkg = "g"
      } else if (amnt(1) == "g") {
        nro = nro / 65
        dlkg = "dl"
      } else "Please measure flour in grams or desiliters"
    } //close flour else if
  }

  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}