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
    
    private val recipeBox = new TextArea(27, 92) {
      editable = false
      wordWrap = true
      lineWrap = true
    }
    
    private val pantryButton = new Button("Pantry")
    private val addRecipeButton = new Button("Add Recipe")
    private val randomer = new Button("Choose For Me")
    
    private val searchBar = new TextField(33) {
      editable = true
    }
    
    private val recBoxScroll = new ScrollPane(recipeBox) {
      minimumSize = new Dimension(740, 485)
      maximumSize = minimumSize
    }
    
    // Empty-looking space to help align the buttons nicely
    private val emptySpace = new TextArea(5, 33) {
      editable = false
      opaque = false
      wordWrap = true
      lineWrap = true
    }
    
    private val NField = new TextField(1) {
      text = "0"
      editable = true
    }
    
    private val useRecBtn = new Button("Make this!")
    
    // Items needed for adding a recipe, displayed only in recipe adding view
    private val addRecBtn = new Button("Done, save!") 
    
    private val ingName = new TextField(20)   { name = "ingName" }
    private val ingName2 = new TextField(20)  { name = "ingName" }
    private val ingName3 = new TextField(20)  { name = "ingName" }
    private val ingName4 = new TextField(20)  { name = "ingName" }
    private val ingName5 = new TextField(20)  { name = "ingName" }
    private val ingName6 = new TextField(20)  { name = "ingName" }
    private val ingName7 = new TextField(20)  { name = "ingName" }
    private val ingName8 = new TextField(20)  { name = "ingName" }
    private val ingName9 = new TextField(20)  { name = "ingName" }
    private val ingName10 = new TextField(20)  { name = "ingName" }
    private val ingName11 = new TextField(20)  { name = "ingName" }
    private val ingName12 = new TextField(20)  { name = "ingName" }
    private val ingName13 = new TextField(20)  { name = "ingName" }
    private val ingName14 = new TextField(20)  { name = "ingName" }
    private val ingName15 = new TextField(20)  { name = "ingName" }
    
    private val ingAmnt = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt2 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt3 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt4 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt5 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt6 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt7 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt8 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt9 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt10 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt11 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt12 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt13 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt14 = new TextField(20)  { name = "ingAmnt" }
    private val ingAmnt15 = new TextField(20)  { name = "ingAmnt" }
    
    private val recName = new TextField(30)
    
    private val aller = new TextField(30)  { name = "aller" }
    private val aller2 = new TextField(30)  { name = "aller" }
    private val aller3 = new TextField(30)  { name = "aller" }
    private val aller4 = new TextField(30)  { name = "aller" }
    private val aller5 = new TextField(30)  { name = "aller" }
    private val aller6 = new TextField(30)  { name = "aller" }
    private val aller7 = new TextField(30)  { name = "aller" }
    private val aller8 = new TextField(30)  { name = "aller" }
    private val aller9 = new TextField(30)  { name = "aller" }
    private val aller10 = new TextField(30)  { name = "aller" }
    private val aller11 = new TextField(30)  { name = "aller" }
    private val aller12 = new TextField(30)  { name = "aller" }
    private val aller13 = new TextField(30)  { name = "aller" }
    private val aller14 = new TextField(30)  { name = "aller" }
    private val aller15 = new TextField(30)  { name = "aller" }
    
    private val recMeth = new TextArea(5, 30) {
      editable = true
      lineWrap = true
      wordWrap = true
    }
    
    private val recScroll = new ScrollPane(recMeth)
    
    private val shoppingAmount = new TextField(1)
    private val shoppingName = new TextField(1)
    private val shoppingAdd = new Button("Add")
    private val shoppingRemove = new Button("Reduce")
    private val shoppingAller = new TextField(1)
    private val shopText = new TextArea(10, 88) { 
      editable = false
      lineWrap = true
      wordWrap = true
      text = "Here you can add ingredients or update an existing amount after shopping or throwing away the ingredient.\n" +
             "Allergen only needs to be filled when adding a previously unknown ingredient which contains said allergen.\n\n" +
             "For example:\nAmount: 1 dl of banana Contains allergen: banana"
      }
    private val toShopButton = new Button("Add or remove ingredients")
    
    private val shopButtonRow = new BoxPanel(Orientation.Horizontal) {
      contents += Swing.HStrut(60)
      contents += shoppingAdd
      contents += Swing.HStrut(40)
      contents += shoppingRemove
    }
    
    private val shoppingRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Amount: ")
      contents += shoppingAmount
      contents += new Label(" of ")
      contents += shoppingName
      contents += new Label(" Contains allergen:")
      contents += shoppingAller
    }
    
    private val NRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Allow missing: ")
      contents += NField
    }
    
    private val ingredientRow = new BoxPanel(Orientation.Horizontal) {
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
    
    private val ingredientRow2 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow3 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow4 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow5 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow6 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow7 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow11 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow12 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow13 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow14 = new BoxPanel(Orientation.Horizontal) {
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
        
    private val ingredientRow15 = new BoxPanel(Orientation.Horizontal) {
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
    private val fieldList = List(
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
        
    private val shoppingView = new BoxPanel(Orientation.Vertical) {
      contents += shopText
      contents += Swing.VStrut(40)
      contents += shoppingRow
      contents += Swing.VStrut(30)
      contents += shopButtonRow
      contents += Swing.VStrut(200)
    }
    
    private val addRecView = new BoxPanel(Orientation.Vertical) {
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
      layout += recBoxScroll                -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 5, 10), 0, 0)
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
              val tryThis = Search.dealWithInput(command)
              if (tryThis == "fail") {
                updateUI("showComplaint")
                recipeBox.text = "There are no recipes matching your search.\nFeel free to try again."
              }
              else {
                updateUI("showRecipe")
                recipeBox.text = tryThis
              }
            }
          }
        } else if (keyEvent.source == this.NField) {
          try {
            val nro = NField.text.toInt
            if (nro >= 0) {
              Search.N = nro
              emptySpace.text = ""
            }
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
          emptySpace.text = "Here you can add a recipe.\nFirst give the name and method, then the ingredients in format "+
                            "100 g (of) spring onion (contains allergen:) onion"
        } else if (buttonEvent.source == this.useRecBtn) { 
          emptySpace.text = Pantry.useRecipe(Search.ingredientMap)
        } else if (buttonEvent.source == this.randomer) {
          val random = Search.dealWithInput("")
          if (random == "fail") {
            updateUI("showComplaint")
            recipeBox.text = "You don't have enough ingredients for any recipe.\nSorry."
          }
          else {
            updateUI("showRecipe")
            recipeBox.text = random
          }
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
                            if (!Reader.checkNumberInput(field.text.trim)) problemFound = true
                            else {
                              ingredientString = ingredientString + field.text.trim.toLowerCase + " § " + friendName.trim.toLowerCase
                              pantryString = pantryString + field.text.trim.toLowerCase + " § " + friendName.trim.toLowerCase
                              if (friendAller != "") {
                                ingredientString = ingredientString + " ¤ "
                                pantryString = pantryString + " § " + friendAller.trim.toLowerCase + " ¤ "
                              }
                              else {
                                ingredientString = ingredientString + " ¤ "
                                pantryString = pantryString + " ¤ "
                              }
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
                  emptySpace.text = "Cool, a new recipe! Saved it."
                }
                else emptySpace.text = "Some ingredient info is missing! Did you remember the format? 100 g (of) raisins, or 1 (of) banana!"
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
    Reader.readRecipes("", "") // Recipes are added to known recipes only when they are read, so let's quietly roll a read at the start
    
    def updateUI(input: String) = {
      if (input == "addingRecipe") {
        for (field <- fieldList) {
          field.text = ""
        }
        recMeth.text = ""
        recName.text = ""
        recBoxScroll.visible = false
        shoppingView.visible = false
        addRecView.visible = true
        useRecBtn.visible = false
        emptySpace.text = ""
      } else if (input == "pantry") {
        recBoxScroll.visible = true
        shoppingView.visible = false
        addRecView.visible = false
        useRecBtn.visible = false
        recipeBox.text = Pantry.pantryInfo
        emptySpace.text = ""
      } else if (input == "shopping") {
        recBoxScroll.visible = false
        addRecView.visible = false
        useRecBtn.visible = false
        shoppingView.visible = true
        emptySpace.text = ""
      } else if (input == "showRecipe") {
        recBoxScroll.visible = true
        addRecView.visible = false
        shoppingView.visible = false
        useRecBtn.visible = true
        emptySpace.text = ""
      } else if (input == "showComplaint") {
        recBoxScroll.visible = true
        addRecView.visible = false
        shoppingView.visible = false
        useRecBtn.visible = false
        emptySpace.text = ""
      }
    }
    
    this.recipeBox.text = Pantry.openingMessage
  }
}