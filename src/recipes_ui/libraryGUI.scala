package recipes_ui

import scala.swing._
import scala.swing.event._
import scala.swing.GridBagPanel.Anchor._
import scala.swing.GridBagPanel.Fill
import javax.swing.UIManager
import java.awt.Font
import scala.swing.Font
import recipes_matter._
import scala.collection.immutable.List

object libraryGUI extends SimpleSwingApplication {
  
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName) 
  
  def top = new MainFrame {
    title = "Recipe Recipient"
    size = new Dimension(300, 300)
    centerOnScreen
    
    val recipeBox = new TextArea(28, 66) {
      editable = false
      wordWrap = true
      lineWrap = true
    }
    
    val pantryButton = new Button("Pantry")
    val addRecipeButton = new Button("Add Recipe")
    val randomer = new Button("Choose For Me")
    
    val searchBar = new TextField(33) {
      editable = true
    }
    
    val recBoxScroll = new ScrollPane(recipeBox)
    
    // Empty-looking space to help align the buttons nicely
    val emptySpace = new TextArea(5, 33) {
      editable = false
      opaque = false
      wordWrap = true
      lineWrap = true
    }
    
    val NField = new TextField(1) {
      text = "0"
      editable = true
    }
    // Items needed for adding a recipe, displayed only in recipe adding view
    
    val addRecBtn = new Button("Done, save!") 
    
    val useRecBtn = new Button("Make this!")
    
    val ingName = new TextField(20)   { name = "ingName" }
    val ingName2 = new TextField(20)  { name = "ingName" }
    val ingName3 = new TextField(20)  { name = "ingName" }
    val ingName4 = new TextField(20)  { name = "ingName" }
    val ingName5 = new TextField(20)  { name = "ingName" }
    val ingName6 = new TextField(20)  { name = "ingName" }
    val ingName7 = new TextField(20)  { name = "ingName" }
    val ingName8 = new TextField(20)  { name = "ingName" }
    val ingName9 = new TextField(20)  { name = "ingName" }
    val ingName10 = new TextField(20)  { name = "ingName" }
    val ingName11 = new TextField(20)  { name = "ingName" }
    val ingName12 = new TextField(20)  { name = "ingName" }
    val ingName13 = new TextField(20)  { name = "ingName" }
    val ingName14 = new TextField(20)  { name = "ingName" }
    val ingName15 = new TextField(20)  { name = "ingName" }
    
    val ingAmnt = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt2 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt3 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt4 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt5 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt6 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt7 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt8 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt9 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt10 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt11 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt12 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt13 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt14 = new TextField(20)  { name = "ingAmnt" }
    val ingAmnt15 = new TextField(20)  { name = "ingAmnt" }
    
    val recName = new TextField(30)
    
    val aller = new TextField(30)  { name = "aller" }
    val aller2 = new TextField(30)  { name = "aller" }
    val aller3 = new TextField(30)  { name = "aller" }
    val aller4 = new TextField(30)  { name = "aller" }
    val aller5 = new TextField(30)  { name = "aller" }
    val aller6 = new TextField(30)  { name = "aller" }
    val aller7 = new TextField(30)  { name = "aller" }
    val aller8 = new TextField(30)  { name = "aller" }
    val aller9 = new TextField(30)  { name = "aller" }
    val aller10 = new TextField(30)  { name = "aller" }
    val aller11 = new TextField(30)  { name = "aller" }
    val aller12 = new TextField(30)  { name = "aller" }
    val aller13 = new TextField(30)  { name = "aller" }
    val aller14 = new TextField(30)  { name = "aller" }
    val aller15 = new TextField(30)  { name = "aller" }
    
    val recMeth = new TextArea(5, 30) {
      editable = true
      lineWrap = true
      wordWrap = true
    }
    
    val recScroll = new ScrollPane(recMeth)
    
    val shoppingAmount = new TextField(1)
    val shoppingName = new TextField(1)
    val shoppingAdd = new Button("Add")
    val shoppingRemove = new Button("Reduce")
    val shoppingAller = new TextField(1)
    val shopText = new TextArea(10, 88) { 
      editable = false
      lineWrap = true
      wordWrap = true
      text = "Here you can add ingredients or update an existing amount after shopping or throwing away the ingredient.\n" +
             "Allergen only needs to be filled when adding a previously unknown ingredient which contains said allergen.\n\n" +
             "For example:\nAmount: 1 dl of banana Contains allergen: banana"
      }
    val toShopButton = new Button("Add or remove ingredients")
    
    val shopButtonRow = new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HStrut(60)
      contents += shoppingAdd
      contents += Swing.HStrut(40)
      contents += shoppingRemove
    }
    
    val shoppingRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += shoppingAmount
      contents += new Label(" of ")
      contents += shoppingName
      contents += new Label(" Contains allergen:")
      contents += shoppingAller
    }
    
    val NRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Allow missing: ")
      contents += NField
    }
    
    val ingredientRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller
    }
    
    val ingredientRow2 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt2
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName2
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller2
    }
        
    val ingredientRow3 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt3
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName3
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller3
    }
        
    val ingredientRow4 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt4
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName4
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller4
    }
        
    val ingredientRow5 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt5
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName5
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller5
    }
        
    val ingredientRow6 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt6
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName6
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller6
    }
        
    val ingredientRow7 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt7
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName7
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller7
    }
        
    val ingredientRow8 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt8
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName8
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller8
    }
        
    val ingredientRow9 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt9
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName9
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller9
    }
        
    val ingredientRow10 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt10
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName10
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller10
    }
        
    val ingredientRow11 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt11
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName11
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller11
    }
        
    val ingredientRow12 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt12
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName12
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller12
    }
        
    val ingredientRow13 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt13
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName13
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller13
    }
        
    val ingredientRow14 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt14
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName14
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller14
    }
        
    val ingredientRow15 = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += ingAmnt15
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingName15
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller15
    }
    
    // Let's add all the fields to a List for easier checking later on
    val fieldList = List(
        ingAmnt, ingName, aller,
        ingAmnt2, ingName2, aller2,
        ingAmnt3, ingName3, aller3,
        ingAmnt4, ingName4, aller4,
        ingAmnt5, ingName5, aller5,
        ingAmnt6, ingName6, aller6,
        ingAmnt7, ingName7, aller7,
        ingAmnt8, ingName8, aller8,
        ingAmnt9, ingName9, aller9,
        ingAmnt10, ingName10, aller10,
        ingAmnt11, ingName11, aller11,
        ingAmnt12, ingName12, aller12,
        ingAmnt13, ingName13, aller13,
        ingAmnt14, ingName14, aller14,
        ingAmnt15, ingName15, aller15
        )
        
    val shoppingView = new BoxPanel(Orientation.Vertical) {
      contents += shopText
      contents += Swing.VStrut(40)
      contents += shoppingRow
      contents += Swing.VStrut(30)
      contents += shopButtonRow
      contents += Swing.VStrut(200)
    }
    
    var addRecView = new BoxPanel(Orientation.Vertical) {
      contents += recName
      contents += Swing.VStrut(10)
      contents += recScroll
      contents += Swing.VStrut(10)
      contents += ingredientRow
      contents += Swing.VStrut(1)
      contents += ingredientRow2
      contents += Swing.VStrut(1)
      contents += ingredientRow3
      contents += Swing.VStrut(1)
      contents += ingredientRow4
      contents += Swing.VStrut(1)
      contents += ingredientRow5
      contents += Swing.VStrut(1)
      contents += ingredientRow6
      contents += Swing.VStrut(1)
      contents += ingredientRow7
      contents += Swing.VStrut(1)
      contents += ingredientRow8
      contents += Swing.VStrut(1)
      contents += ingredientRow9
      contents += Swing.VStrut(1)
      contents += ingredientRow10
      contents += Swing.VStrut(1)
      contents += ingredientRow11
      contents += Swing.VStrut(1)
      contents += ingredientRow12
      contents += Swing.VStrut(1)
      contents += ingredientRow13
      contents += Swing.VStrut(1)
      contents += ingredientRow14
      contents += Swing.VStrut(1)
      contents += ingredientRow15
      contents += Swing.VStrut(15)
      contents += addRecBtn
    }
  
    // Layout
    this.contents = new GridBagPanel {
      layout += recipeBox                -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 5, 10), 0, 0)
      layout += pantryButton             -> new Constraints(1, 2, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += searchBar                -> new Constraints(1, 0, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += NRow                     -> new Constraints(1, 1, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += addRecipeButton          -> new Constraints(1, 3, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += randomer                 -> new Constraints(1, 4, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += toShopButton             -> new Constraints(1, 5, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += useRecBtn                -> new Constraints(1, 6, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 10), 0, 0)
      layout += emptySpace               -> new Constraints(1, 7, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(0, 0, 0, 10), 0, 0)
      layout += addRecView               -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 5, 10), 0, 0)
      layout += shoppingView             -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 5, 10), 0, 0)
      }
    
    // Listen & react
    listenTo(pantryButton)
    listenTo(searchBar.keys)
    listenTo(addRecipeButton)
    listenTo(addRecBtn)
    listenTo(recMeth.keys)
    listenTo(randomer)
    listenTo(recName.keys)
    listenTo(NField.keys)
    listenTo(toShopButton)
    listenTo(shoppingAdd)
    listenTo(shoppingRemove)
    listenTo(shoppingAmount.keys)
    listenTo(shoppingName.keys)
    listenTo(shoppingAller.keys)
    listenTo(useRecBtn)
    for (field <- fieldList) {
      listenTo(field)
    }
    
    this.reactions += {
      case keyEvent: KeyPressed =>
        if (keyEvent.source == this.searchBar && keyEvent.key == Key.Enter) {
          val command = this.searchBar.text.trim
          if (command.nonEmpty) {
            if (!Reader.checkSmartInput(command)) emptySpace.text = "Please don't search with special characters."
            else if (!Search.checkInput(command)) emptySpace.text = "You can use one good and one bad word in the search.\nNo more than that, please."
            else {
              updateUI("showRecipe")
              val tryThis = Search.dealWithInput(command)
              if (tryThis == "fail") recipeBox.text = "There are no recipes matching your search.\nFeel free to try again."
              else recipeBox.text = tryThis
            }
          }
        } else if (keyEvent.source == this.NField) {
          try {
            val nro = NField.text.toInt
            if (nro >= 0) Search.N = nro
            else emptySpace.text = "Use a positive number for allowance of missing ingredients."
          } catch {
            case e: Exception => emptySpace.text = "Please use numbers for allowance of missing ingredients."
          }
        }
      case buttonEvent: ButtonClicked =>
        if (buttonEvent.source == this.pantryButton) {
          updateUI("pantry")
        } else if (buttonEvent.source == this.addRecipeButton) {
          updateUI("addingRecipe")
        } else if (buttonEvent.source == this.useRecBtn) {
          emptySpace.text = Pantry.useRecipe(Search.ingredientMap)
        } else if (buttonEvent.source == this.randomer) {
          updateUI("showRecipe")
          val random = Search.dealWithInput("")
          if (random == "fail") recipeBox.text = "You don't have enough ingredients for any recipe.\nSorry."
          else recipeBox.text = random
        } else if (buttonEvent.source == this.toShopButton) {
          updateUI("shopping")
        } else if (buttonEvent.source == this.shoppingAdd) {
          val notMissing: Boolean = {
            if (shoppingName.text != "") shoppingAmount.text != ""
            else if (shoppingAmount.text != "") shoppingName.text != ""
            else false
          }
          if (Reader.checkSmartInput(shoppingAmount.text) && Reader.checkSmartInput(shoppingName.text) && Reader.checkSmartInput(shoppingAller.text) && notMissing) {
            this.shopText.text = Pantry.changeAmount(shoppingAmount.text.trim.toLowerCase, shoppingName.text.trim.toLowerCase, shoppingAller.text.trim.toLowerCase, "add")
          } else emptySpace.text = "Let's not use any special characters."
        } else if (buttonEvent.source == this.shoppingRemove) {
          val notMissing: Boolean = {
            if (shoppingName.text != "") shoppingAmount.text != ""
            else if (shoppingAmount.text != "") shoppingName.text != ""
            else false
          }
          if (Reader.checkSmartInput(shoppingAmount.text) && Reader.checkSmartInput(shoppingName.text) && notMissing) {
            this.shopText.text = Pantry.changeAmount(shoppingAmount.text.trim.toLowerCase, shoppingName.text.trim.toLowerCase, "", "reduce")
          } else emptySpace.text = "Let's not use any special characters."
        } else if (buttonEvent.source == this.addRecBtn) {
          if (recName.text != "" && recMeth.text != "") {
            if (ingName.text == "" || ingAmnt.text == "") emptySpace.text = "Let's fill the ingredients.\nFrom the top, please."
            else {
              if (Reader.checkSmartInput(recName.text) && Reader.checkSmartInput(recMeth.text)) {
                var problemFound = false
                var missingFound = false
                var ingredientString = ""
                var pantryString = ""
                for (field <- fieldList) {
                  if (!Reader.checkSmartInput(field.text.trim.toLowerCase)) { // Checking whether the field contains special chars
                    problemFound = true
                    emptySpace.text = "Let's not use any special characters."
                  }
                  else {
                    if (!problemFound) {
                      if (field.name.charAt(3) == 'A') { // For every Amount field, see if name and allergen are also filled out
                        val friendName = fieldList(fieldList.indexOf(field)+1).text.toLowerCase
                        val friendAller = fieldList(fieldList.indexOf(field)+2).text.toLowerCase
                        if (field.text != "") {
                          if (friendName == "") { // If Amount field is empty, name field needs to be empty too
                            missingFound = true
                          }
                          else {
                            ingredientString = ingredientString + field.text.trim.toLowerCase + " � " + friendName.trim.toLowerCase
                            pantryString = pantryString + field.text.trim.toLowerCase + " � " + friendName.trim.toLowerCase
                            if (friendAller != "") {
                              ingredientString = ingredientString + " � "
                              pantryString = pantryString + " � " + friendAller.trim.toLowerCase + " � "
                            }
                            else {
                              ingredientString = ingredientString + " � "
                              pantryString = pantryString + " � "
                            }
                          }
                        } else {
                          if (friendName != "" && friendAller != "") missingFound = true
                        } // close emptiness else
                      } // close 4th letter checking if
                    } else emptySpace.text = "Let's not use any special characters."
                  }
                }
                if (!missingFound && !problemFound) {
                  Reader.recipeAdder(recName.text.trim + "#" + recMeth.text.trim + "#" + ingredientString) //No problems, so all info sent to be added to the recipe library
                  Reader.ingredientAdder(pantryString, "recipe") //We can also add all the ingredient to the pantry since the recipe is acceptable
                }
                else emptySpace.text = "Some ingredient info is missing! Please fix."
              } else emptySpace.text = "Let's not us nany special characters."
            }
          } else emptySpace.text = "Your recipe needs a name and a method."
        }
      }
        
    
    // Setting up the initial state
    addRecView.visible = false
    shoppingView.visible = false
    useRecBtn.visible = false
    Reader.updatePantry
    
    def updateUI(input: String) = {
      if (input == "addingRecipe") {
        recipeBox.visible = false
        shoppingView.visible = false
        addRecView.visible = true
        useRecBtn.visible = false
        emptySpace.text = ""
      } else if (input == "pantry") {
        recipeBox.visible = true
        shoppingView.visible = false
        addRecView.visible = false
        useRecBtn.visible = false
        recipeBox.text = Pantry.pantryInfo
        emptySpace.text = ""
      } else if (input == "shopping") {
        recipeBox.visible = false
        addRecView.visible = false
        useRecBtn.visible = false
        shoppingView.visible = true
      } else if (input == "showRecipe") {
        recipeBox.visible = true
        addRecView.visible = false
        shoppingView.visible = false
        useRecBtn.visible = true
      }
    }
    
    this.recipeBox.text = Pantry.openingMessage
  }
}