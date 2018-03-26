package recipes_ui

import scala.swing._
import scala.swing.event._
import scala.swing.GridBagPanel.Anchor._
import scala.swing.GridBagPanel.Fill
import javax.swing.UIManager
import java.awt.Font
import scala.swing.Font
import recipes_matter._

object libraryGUI extends SimpleSwingApplication {
  
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName) 
  
  def top = new MainFrame {
    title = "Recipe Recipient"
    size = new Dimension(300, 300)
    centerOnScreen
    
    val pantry = new Pantry
    val reader = new Reader
    
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
    
    // Items needed for adding a recipe, displayed only in recipe adding view
    
    val addRecBtn = new Button("Done, save!") 
//    val addIngBtn = new Button("Add another ingredient") 
    
    val ingName = new TextField(20)
    val ingName2 = new TextField(20) 
    val ingName3 = new TextField(20) 
    val ingName4 = new TextField(20) 
    val ingName5 = new TextField(20) 
    val ingName6 = new TextField(20) 
    val ingName7 = new TextField(20) 
    val ingName8 = new TextField(20) 
    val ingName9 = new TextField(20) 
    val ingName10 = new TextField(20) 
    val ingName11 = new TextField(20) 
    val ingName12 = new TextField(20) 
    val ingName13 = new TextField(20) 
    val ingName14 = new TextField(20)
    val ingName15 = new TextField(20) 
    
    val ingAmnt = new TextField(20)
    val ingAmnt2 = new TextField(20)
    val ingAmnt3 = new TextField(20)
    val ingAmnt4 = new TextField(20)
    val ingAmnt5 = new TextField(20)
    val ingAmnt6 = new TextField(20)
    val ingAmnt7 = new TextField(20)
    val ingAmnt8 = new TextField(20)
    val ingAmnt9 = new TextField(20)
    val ingAmnt10 = new TextField(20)
    val ingAmnt11 = new TextField(20)
    val ingAmnt12 = new TextField(20)
    val ingAmnt13 = new TextField(20)
    val ingAmnt14 = new TextField(20)
    val ingAmnt15 = new TextField(20)
    
    val recName = new TextField(30)
    
    val aller = new TextField(30)
    val aller2 = new TextField(30)
    val aller3 = new TextField(30)
    val aller4 = new TextField(30)
    val aller5 = new TextField(30)
    val aller6 = new TextField(30)
    val aller7 = new TextField(30)
    val aller8 = new TextField(30)
    val aller9 = new TextField(30)
    val aller10 = new TextField(30)
    val aller11 = new TextField(30)
    val aller12 = new TextField(30)
    val aller13 = new TextField(30)
    val aller14 = new TextField(30)
    val aller15 = new TextField(30)    
    
    val recMeth = new TextArea(5, 30)
    
    val ingredientRow = new BoxPanel(Orientation.Horizontal) {
      contents += ingName
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller
    }
    
    val ingredientRow2 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName2
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt2
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller2
    }
        
    val ingredientRow3 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName3
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt3
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller3
    }
        
    val ingredientRow4 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName4
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt4
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller4
    }
        
    val ingredientRow5 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName5
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt5
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller5
    }
        
    val ingredientRow6 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName6
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt6
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller6
    }
        
    val ingredientRow7 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName7
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt7
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller7
    }
        
    val ingredientRow8 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName8
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt8
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller8
    }
        
    val ingredientRow9 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName9
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt9
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller9
    }
        
    val ingredientRow10 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName10
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt10
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller10
    }
        
    val ingredientRow11 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName11
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt11
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller11
    }
        
    val ingredientRow12 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName12
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt12
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller12
    }
        
    val ingredientRow13 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName13
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt13
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller13
    }
        
    val ingredientRow14 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName14
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt14
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller14
    }
        
    val ingredientRow15 = new BoxPanel(Orientation.Horizontal) {
      contents += ingName15
      contents += Swing.HStrut(5)
      contents += new Label("of")
      contents += Swing.HStrut(5)
      contents += ingAmnt15
      contents += Swing.HStrut(5)
      contents += new Label("Contains allergen:")
      contents += Swing.HStrut(5)
      contents += aller15
    }
    
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
      contents += Swing.VStrut(10)
      contents += Swing.VStrut(5)
      contents += addRecBtn
    }
  
    // Layout
    this.contents = new GridBagPanel {
      layout += recipeBox                -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      layout += pantryButton             -> new Constraints(1, 1, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 5), 0, 0)
      layout += searchBar                -> new Constraints(1, 0, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 5), 0, 0)
      layout += addRecipeButton          -> new Constraints(1, 2, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 5), 0, 0)
      layout += randomer                 -> new Constraints(1, 3, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 0, 0, 5), 0, 0)
      layout += emptySpace               -> new Constraints(1, 4, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(0, 0, 0, 5), 0, 0)
      layout += addRecView               -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 10, 0, 10), 0, 0)
      }
    
    // Listen & react
    listenTo(pantryButton)
    listenTo(searchBar)
    listenTo(addRecipeButton)
    listenTo(addRecBtn)
    
    this.reactions += {
      case keyEvent: KeyPressed =>
        if (keyEvent.source == this.searchBar && keyEvent.key == Key.Enter) {
          val command = this.searchBar.text.trim
          if (command.nonEmpty) {
            this.searchBar.text = ""
            //TODO: make it do something
          }
        }
      case buttonEvent: ButtonClicked =>
        if (buttonEvent.source == this.pantryButton) {
          recipeBox.text = pantry.pantryInfo
        } else if (buttonEvent.source == this.addRecipeButton) {
          updateUI("addingRecipe")
          //TODO: make it work
        } else if (buttonEvent.source == this.randomer) {
          //TODO: give random recipe
        } else if (buttonEvent.source == this.addRecBtn) {
          println("Adding recipe")
          reader.recipeAdder("banana")
        }
        
    }
    
    // Setting up the initial state
    addRecView.visible = false
    
    def updateUI(input: String) = {
      if (input == "addingRecipe") {
        recipeBox.visible = false
        addRecView.visible = true     
      } 
    }
    
    this.recipeBox.text = pantry.openingMessage
  }
}