package app

import scala.collection.mutable._
import scala.collection.immutable

class Group (var name : String) extends Element(name) {
  private var list: List[Element] = List()

  def print(n: Int): Unit = {
    var res: String = ""

    var count: Int = this.count()

    if (n == 0) {
      res = name + count
    } else {
      res = "└" + "─".repeat(n) + name
    }
    
    println(res)

    list.foreach(el => {
      el.print(n + 2)
    })
  }

  def calculate(n: Int): String = {
    var res: String = ""

    var count: String = " (" + this.count().toString + ")\n"

    if (n == 0) {
      res = name + count
    } else {
      res = "└" + "─".repeat(n) + name + count
    }
    
    list.foreach(el => {
      res += el.calculate(n + 2)
    })

    return res
  }

  def calculateFlat(): List[(String, Int)] = {
    var res: List[(String, Int)] = List((name, this.count()))
    
    list.foreach(el => {
      res = res ++ el.calculateFlat()
    })

    return res.sortWith(_._2 > _._2)
  }

  def count(): Int = {
    var res = 0
    list.foreach(el => {
      res += el.count()
    })

    return res
  }

  def add(el : Element) = {
    list = el :: list
  }
}
