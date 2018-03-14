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
    
    val recipeBox = new TextArea(28, 66) {
      editable = false
      wordWrap = true
      lineWrap = true
    }
    
    val pantryButton = new Button("Pantry") {
      
    }
    
    val addRecipeButton = new Button("Add Recipe") {
      
    }
    
    val searchBar = new TextField(33) {
      editable = true
    }
    
    // Empty-looking space to help align the buttons nicely
    val emptySpace = new TextArea(5, 33) {
      editable = false
      opaque = false
    }
  
    // Layout
    this.contents = new GridBagPanel {
      layout += recipeBox                -> new Constraints(0, 0, 1, 0, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      layout += pantryButton             -> new Constraints(1, 1, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      layout += searchBar                -> new Constraints(1, 0, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      layout += addRecipeButton          -> new Constraints(1, 2, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      layout += emptySpace               -> new Constraints(1, 3, 1, 1, 0, 0, NorthWest.id, Fill.Both.id, new Insets(5, 5, 5, 5), 0, 0)
      }
    
    // Listen & react
    listenTo(pantryButton)
    listenTo(searchBar)
    listenTo(addRecipeButton)
    
    this.pack()
    this.recipeBox.text = pantry.openingMessage
  }
}