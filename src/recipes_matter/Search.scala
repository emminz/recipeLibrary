package recipes_matter

import recipes_ui._

object Search {
  
  var N = 0

  // When input comes in, we need to know whether it's a like or a dislike
  // Returns the recipe as a string formatted and ready to be displayed
  def dealWithInput(input: String): String = {
    var avoid = ""
    var like = ""
    if (input.contains(',')) {
      val split = input.trim.split(',')
      if (split(0).head == '!') {
        avoid = split(0).drop(1).trim.toLowerCase
        like = split(1).trim.toLowerCase
      }
      else if (split(1).head == '!') {
        avoid = split(1).drop(1).trim.toLowerCase
        like = split(0).trim.toLowerCase
      }
    } else if (input != "") {
      if (input.head == '!') avoid = input.drop(1)
      else like = input.trim.toLowerCase
    }
    println("I see that you like " + like + " and hate " + avoid)
    val suitables = Reader.readRecipes(like, avoid)
    if (suitables.nonEmpty) {
      val chosen = pickOne(suitables)
      giveRecipe(chosen)
    } else {
      "fail"
    }
  }
  
  def giveRecipe(chosen: collection.mutable.Map[String, Array[String]]): String = {
    var ingredientString = ""
    val name = chosen.keys.head.toString
    val method = chosen(name)(0)
    val ingredients = chosen(name)(1).trim
    val individuals = ingredients.trim.split('¤')
    for (osa <- individuals) {
      val amnt = osa.split('§')(0).trim
      val ing = osa.split('§')(1).trim
      ingredientString = ingredientString + amnt + " of " + ing + "\n"
    }
    name + "\n\n" + method + "\n\n" + ingredientString
  }
  
  def pickOne(suitables: collection.mutable.Map[String, Array[String]]): collection.mutable.Map[String, Array[String]] = {
    val keys = suitables.keySet
    val chosenName = keys.head
    collection.mutable.Map(chosenName -> suitables(chosenName))
  }
  
  // Checks if there's only one like and one dislike, returns true
  def checkInput(input: String) = {
    var tooMany = false
    if (input.contains(',')) {
      val split = input.split(',')
      if (split.length > 2) tooMany = true
      else {
        if (split(0).head == '!' && split(1).head == '!') tooMany = true
        else if (split(0).head != '!' && split(1).head != '!') tooMany = true
      }
    }
    !tooMany
  }
  
}