package recipes_matter

import java.io.BufferedReader
import java.io.IOException
import scala.io.Source
import java.io.{FileNotFoundException, IOException}

class Reader {
  val filename = "no-such-file.scala"
  try {
      for (line <- Source.fromFile(filename).getLines) {
          println(line)
      }
  } catch {
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Got an IOException!")
  }
}