package recipes_matter

import recipes_ui._

object Search {
  val knownRecipes  = collection.mutable.Map[String, Array[String]]()
  var N = 0 // This is the allow missing counter, defaulting to 0
  val ingredientMap = collection.mutable.Map[String, String]()

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
    val suitables = Reader.readRecipes(like, avoid)
    if (suitables.nonEmpty) {
      val chosen = pickOne(suitables)
      giveRecipe(chosen)
    } else {
      "fail"
    }
  }
  
  // Prepares the recipe into a string for the UI recipeBox element to display
  def giveRecipe(chosen: collection.mutable.Map[String, Array[String]]): String = {
    var ingredientString = ""
    val name = chosen.keys.head.toString
    val method = chosen(name)(0).replace('¤', '\n')
    val ingredients = chosen(name)(1).trim
    val individuals = ingredients.trim.split('¤')
    for (osa <- individuals) {
      val amnt = osa.split('§')(0).trim
      val ing = osa.split('§')(1).trim
      if (!ingredientMap.contains(ing)) ingredientMap.update(ing, amnt)
      else {
        var newAmnt = 0.0
        var finalAmnt = ""
        var savedlkg = ""
        var oldAmnt = {
          if (amnt.contains(' ')) amnt(0).toDouble
          else amnt.toDouble
        }
        if (ingredientMap(ing).contains(' ')) {
          newAmnt = ingredientMap(ing).split(' ')(0).toDouble
          savedlkg = ingredientMap(ing).split(' ')(1)
          newAmnt = newAmnt + oldAmnt
          finalAmnt = newAmnt.toString + " " + savedlkg
        }
        else finalAmnt = amnt
        ingredientMap.update(ing, finalAmnt)
      }
      ingredientString = ingredientString + amnt + " of " + ing + "\n"
    }
    name + "\n\n" + method + "\n\n" + ingredientString
  }
  
  // Chooses a recipe randomly if there are multiple options. Otherwise hands out the only option
  def pickOne(suitables: collection.mutable.Map[String, Array[String]]): collection.mutable.Map[String, Array[String]] = {
    val keys = suitables.keySet
    val r = scala.util.Random
    val chosenName = if (keys.size == 1) {
      keys.head
    } else {
      keys.toBuffer(r.nextInt(keys.size - 1 ))
    }
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