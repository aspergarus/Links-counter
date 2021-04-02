package app

import scala.collection.mutable._
import scala.collection.immutable

abstract class Element (name : String) {
  def print(n: Int): Unit
  def calculate(n: Int): String
  def count(): Int
  def calculateFlat(): List[(String, Int)] 
}
