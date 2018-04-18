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
    
    // Empty-looking space to help align the buttons nicely
    val emptySpace = new TextArea(5, 33) {
      editable = false
      opaque = false
    }
    
    val NField = new TextField(1) {
      text = "0"
      editable = true
    }
    // Items needed for adding a recipe, displayed only in recipe adding view
    
    val addRecBtn = new Button("Done, save!") 
//    val addIngBtn = new Button("Add another ingredient") 
    
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
    
    val recMeth = new TextArea(5, 30)
    
    val NRow = new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Allow missing: ")
      contents += NField
    }
    
    val ingredientRow = new BoxPanel(Orientation.Horizontal) {
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
    
    var addRecView = new BoxPanel(Orientation.Vertical) {
      contents += recName
      contents += Swing.VStrut(10)
      contents += recMeth
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
      layout += emptySpace               -> new Constraints(1, 5, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(0, 0, 0, 10), 0, 0)
      layout += addRecView               -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 5, 10), 0, 0)
      }
    
    // Listen & react
    listenTo(pantryButton)
    listenTo(searchBar.keys)
    listenTo(addRecipeButton)
    listenTo(addRecBtn)
    listenTo(recMeth.keys)
    listenTo(recName.keys)
    listenTo(NField.keys)
    for (field <- fieldList) {
      listenTo(field)
    }
    
    this.reactions += {
      case keyEvent: KeyPressed =>
        if (keyEvent.source == this.searchBar && keyEvent.key == Key.Enter) {
          val command = this.searchBar.text.trim
          if (command.nonEmpty) {
            if (!Reader.checkSmartInput(command)) emptySpace.text = "Please don't search with\nspecial characters."
            else if (!Search.checkInput(command)) emptySpace.text = "You can use one good\nand one bad word\nin the search.\nNo more than that, please."
            else {
              val tryThis = Search.dealWithInput(command)
              if (tryThis == "fail") recipeBox.text = "There are no recipes matching your searh.\nFeel free to try again."
              else recipeBox.text = tryThis
            }
          }
        } else if (keyEvent.source == this.NField) {
          try {
            val nro = NField.text.toInt
            if (nro >= 0) Search.N = nro
            else emptySpace.text = "Use a positive number for\nallowance of missing ingredients."
          } catch {
            case e: Exception => emptySpace.text = "Please use numbers for\nallowance of missing ingredients."
          }
        }
      case buttonEvent: ButtonClicked =>
        if (buttonEvent.source == this.pantryButton) {
          updateUI("pantry")
        } else if (buttonEvent.source == this.addRecipeButton) {
          updateUI("addingRecipe")
        } else if (buttonEvent.source == this.randomer) {
          //TODO: give random recipe
        } else if (buttonEvent.source == this.addRecBtn) {
          println("Adding recipe")
          if (recName.text != "" && recMeth.text != "") {
            if (ingName.text == "" || ingAmnt.text == "") emptySpace.text = "Let's fill the ingredients.\nFrom the top, please."
            else {
              if (Reader.checkSmartInput(recName.text.toString) && Reader.checkSmartInput(recMeth.text.toString)) {
                var problemFound = false
                var missingFound = false
                var ingredientString = ""
                var pantryString = ""
                for (field <- fieldList) {
                  if (!Reader.checkSmartInput(field.text.trim.toString)) { // Checking whether the field contains special chars
                    problemFound = true
                    emptySpace.text = "Let's not use\nany special characters."
                  }
                  else {
                    if (!problemFound) {
                      if (field.name.charAt(3) == 'A') { // For every Amount field, see if name and allergen are also filled out
                        val friendName = fieldList(fieldList.indexOf(field)+1).text
                        val friendAller = fieldList(fieldList.indexOf(field)+2).text
                        if (field.text != "") {
                          if (friendName == "") { // If Amount field is empty, name field needs to be empty too
                            missingFound = true
                          }
                          else {
                            ingredientString = ingredientString + field.text.trim.toString + " § " + friendName.trim.toString
                            pantryString = pantryString + field.text.trim.toString + " § " + friendName.trim.toString
                            if (friendAller != "") {
                              ingredientString = ingredientString + " ¤ "
                              pantryString = pantryString + " § " + friendAller.trim.toString + " ¤ "
                            }
                            else {
                              ingredientString = ingredientString + " ¤ "
                              pantryString = pantryString + " ¤ "
                            }
                          }
                        } else {
                          if (friendName != "" && friendAller != "") missingFound = true
                        } // close emptiness else
                      } // close 4th letter checking if
                    } else emptySpace.text = "Let's not use\nany special characters."
                  }
                }
                if (!missingFound && !problemFound) {
                  Reader.recipeAdder(recName.text.trim.toString + "#" + recMeth.text.trim.toString + "#" + ingredientString) //No problems, so all info sent to be added to the recipe library
                  Reader.ingredientAdder(pantryString) //We can also add all the ingredient to the pantry since the recipe is acceptable
                }
                else emptySpace.text = "Some ingredient info\nis missing! Please fix."
              } else emptySpace.text = "Let's not use\nany special characters."
            }
          } else emptySpace.text = "Your recipe needs\na name and a method."
        }
      }
        
    
    // Setting up the initial state
    addRecView.visible = false
    Reader.updatePantry
    
    def updateUI(input: String) = {
      if (input == "addingRecipe") {
        recipeBox.visible = false
        addRecView.visible = true     
        emptySpace.text = ""
      } else if (input == "pantry") {
        recipeBox.visible = true
        addRecView.visible = false
        recipeBox.text = Pantry.pantryInfo
        emptySpace.text = ""
      }
    }
    
    this.recipeBox.text = Pantry.openingMessage
  }
}