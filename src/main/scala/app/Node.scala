package app

import scala.collection.mutable._
import scala.collection.immutable

class Node (var name : String) extends Element(name) {
  def print(n: Int): Unit = {
    println("└" + "─".repeat(n) + name)
  }

  def calculate(n: Int): String = ""
  def count(): Int = 1
  def calculateFlat(): List[(String, Int)]  = List()
}
