package recipe_matter.tests

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Assertions._
import recipes_matter._
import scala.Vector

@RunWith(classOf[JUnitRunner])
class recipe_tests extends FlatSpec {
  
  "Search.dealWithInput" should "be able to search with a multi-part word" in {
    Search.N = 6
    assert(Search.dealWithInput("soft brown sugar") != "fail")
  }
  
  "Search.chooseOne" should "pick Sima when searching for water with 1 missing" in {
    Search.N = 1
    val chosenRecipeName = Search.dealWithInput("water")
    assert(chosenRecipeName.startsWith("Sima"))
  }
  
  "Search.chooseOne" should "not find Sima when searching for sugar but allergic to citrus" in {
    val chosenRecipeName = Search.dealWithInput("water, !citrus")
    assert(!chosenRecipeName.startsWith("Sima"))
  }
  
  "Pantry.ingredients" should "find 11.0 dl when adding 85 g of sugar, having 10 dl already" in {
    Pantry.ingredients.update("sugar", "10 dl")
    Pantry.changeAmount("85 g", "sugar", "", "add")
    assert(Pantry.ingredients("sugar") == "11.0 dl")
  }
  
  "Pantry.ingredients" should "contain bananas even when the amount is reduced to 0" in {
    Pantry.changeAmount("1", "banana", "", "reduce")
    assert(Pantry.ingredients.contains("banana"))
  }
  
  "Pantry.ingredients" should "contain ingredient when a brand new one is added" in {
    Reader.ingredientAdder("100 g § anpan ¤", "recipe")
    assert(Pantry.ingredients.contains("anpan"))
  }
  
  "Pantry.ingredients" should "have 0 as amount for new ingredients entered through recipe adder" in {
    Reader.ingredientAdder("100 g § test ¤", "recipe")
    assert(Pantry.ingredients("test") == "0.0 g")
  }
  
  "Pantry.ingredients" should "have the input amount for new ingredients entered through ingredient adder" in {
    Reader.ingredientAdder("100 g § test2 ¤", "shop")
    assert(Pantry.ingredients("test2") == "100.0 g")
  }
  
  "Pantry.changeAmount" should "not let amounts fall below 0" in {
    Pantry.ingredients.update("banana", "2")
    val message = Pantry.changeAmount("2", "banana", "", "reduce")
    assert(message == "Cannot reduce! You only have 2 in your pantry.")
  }
  
  "Pantry.changeAmount" should "be able to add decimal amounts" in {
    Pantry.ingredients.update("banana", "2")
    Pantry.changeAmount("0.5", "banana", "", "reduce")
    assert(Pantry.ingredients("banana") == "1.5")
  }
  
}