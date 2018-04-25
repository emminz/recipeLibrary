package recipes_matter

import scala.collection.mutable.Map

object Pantry {
  
  val ingredients = collection.mutable.Map[String, String]()
  val allergens = collection.mutable.Map[String, String]()
  private val convertables = collection.mutable.Map[String, String](("sugar", "85"), ("flour", "65"), ("olive oil", "90"))
  
  def changeAmount(amount: String, ingredient: String, allergen: String, way: String): String = {
    var message = "I'm a placheolder hoping to never pop up."
    if (this.ingredients.contains(ingredient)) {
      var changingAmount = amount
      val dlkg = {
        if (amount.contains(' ')) amount.split(' ')(1)
        else ""
      }
      val compare = {
        if (ingredients(ingredient).contains(' ')) ingredients(ingredient).split(' ')(1)
        else ""
      } // The user cannot at first measure rye bread in pieces and then decide to switch to grams
      if (dlkg != compare && !convertables.contains(ingredient)) {
        var endbit = "units"
        if (compare.toString != "") endbit = compare.toString
        message = "Oops, you're meant to measure " + ingredient + " in " + endbit
      }
      else if (convertables.contains(ingredient)) {
        changingAmount = converter(amount, ingredient)(0) + " " + converter(amount, ingredient)(1)
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
    if (way == "add") { // If we are adding to an ingredient, it's fairly simple
      val newAmount = compare.toDouble + amnt.toDouble
      ingredients.update(name, newAmount.toString + dlkg)
      message = "Amount of " + name + " updated!"
    } else {
      if (compare >= amnt) { // If the amount in pantry is greater than that to be reduced, do the reduction
        val newAmount = compare.toDouble - amnt.toDouble
        ingredients.update(name, newAmount.toString + dlkg)
        message = "Amount of " + name + " updated!"
      } else message = "Cannot reduce! You only have " + ingredients(name) + " in your pantry."
    }
    Reader.updateFile // The changes have so far only been made to Pantry.ingredients. Now let's save them to pantry.txt
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
  
  // Takes in Map like (banana flakes -> 100 g)
  def useRecipe(ingredientsMap: collection.mutable.Map[String, String]): String = {
    for (ing <- ingredientsMap) {
      val amount = {
        if(ing._2.contains(' ')) ing._2.split(' ')(0)
        else ing._2
      }
      val compare = {
        if(this.ingredients(ing._1).contains(' ')) this.ingredients(ing._1).split(' ')(0)
        else this.ingredients(ing._1)
      }
      if (compare.toDouble >= amount.toDouble) changeAmount(ing._2, ing._1, "", "reduce")
      else changeAmount(compare, ing._1, "", "reduce")
    }
    "Yum yum, got it! Reduced the ingredients from the pantry. Happy cooking!"
  }

  def openingMessage: String = "Welcome to the recipe library!\nLet's get cooking."
  
}