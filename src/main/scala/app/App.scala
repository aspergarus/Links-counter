package app

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.image.Image
import scalafx.scene.layout.{GridPane, Priority, VBox}
import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter
import scalafx.collections.ObservableBuffer

import java.io.{FileNotFoundException, PrintWriter, StringWriter}
import scala.language.implicitConversions

import scala.io.Codec
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.collection.mutable._
import scala.collection.immutable

object App extends JFXApp {
  stage = new PrimaryStage {
    scene = new Scene {
      title = "Dialogs Demo"
      root = new VBox {
        children = Seq(
          button("File", fileDialog),
          table
        )
        spacing = 6
        padding = Insets(10)
        alignment = Pos.TopCenter
      }
    }
  }

  def button[R](text: String, action: () => R) = new Button(text) {
    onAction = _ => action()
    alignmentInParent = Pos.Center
    hgrow = Priority.Always
    maxWidth = Double.MaxValue
    padding = Insets(7)
  }

  def fileDialog(): Unit = {
    val fileChooser = new FileChooser {
      title = "Open Resource File"
      extensionFilters ++= Seq(new ExtensionFilter("Text Files", "*.html"))
    }
    val selectedFile = fileChooser.showOpenDialog(stage)

    val foldersData: List[(String, Int)] = fileReader(selectedFile.getAbsolutePath()).calculateFlat()

    folders.clear()
    for (tuple <- foldersData) {
      folders += new Folder(tuple._1, tuple._2)
    }

    println("End")
  }

  lazy val textArea2 = new TextArea {
    prefColumnCount = 90
    hgrow = Priority.Always
    vgrow = Priority.Always
  }

  lazy val folders = ObservableBuffer[Folder](
    new Folder("Test", 1),
    new Folder("Test2", 2),
    new Folder("Test3", 10)
  )

  lazy val table = new TableView[Folder](folders) {
    hgrow = Priority.Always
    vgrow = Priority.Always
    columns ++= List(
      new TableColumn[Folder, String] {
        text = "Folder"
        cellValueFactory = { _.value.name }
        prefWidth = 100
      },
      new TableColumn[Folder, Int] {
        text = "Count"
        cellValueFactory = { _.value.count }
        prefWidth = 100
      },
    )
  }

  def fileReader(source: String): Element = {
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    val headerRegEx = new scala.util.matching.Regex("""<H.+>(.+)<""")
    val linkRegEx = new scala.util.matching.Regex("""<A.+>(.+)<""", "link")

    var DLE = false
    var elem: Group = new Group("All")
    var elems: Stack[Group] = Stack(elem)

    for (myString <- Source.fromFile(source).getLines()) {
      val header = for (m <- headerRegEx.findFirstMatchIn(myString)) yield m.group(1)
      val link = for (m <- linkRegEx.findFirstMatchIn(myString)) yield m.group(1)

      if (myString.contains("</DL>")) {
        DLE = true
      }

      if (header != None) {
        var headerStr = header.getOrElse("Empty")

        elem = new Group(headerStr)

        if (!elems.isEmpty) {
          elems.top.add(elem)
        }
        elems.push(elem)
      }

      if (link != None) {
        elems.top.add(new Node(link.getOrElse("Empty")))
      }

      if (DLE) {
        elem = elems.pop()
        DLE = false
      }
    }

    return elem
  }

}
