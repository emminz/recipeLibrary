package recipes_matter

import recipes_ui._

object Search {

  // When input comes in, we need to know whether it's a like or a dislike
  def dealWithInput(input: String) = {
    var avoid = ""
    var like = ""
    if (input.contains(',')) {
      val split = input.trim.split(',')
      if (split(0).head == '!') {
        avoid = split(0).drop(1)
        like = split(1)
      }
      else if (split(1).head == '!') {
        avoid = split(1).drop(1)
        like = split(0)
      }
    } else {
      if (input.head == '!') avoid = input.drop(1)
      else like = input
    }
    println("I see that you like " + like + " and hate " + avoid)
    Reader.readRecipes(like, avoid)
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