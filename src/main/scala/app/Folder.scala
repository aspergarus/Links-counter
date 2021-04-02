package app

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Folder(_name : String, _count : Int) {
  val name = new StringProperty(this, "name", _name)
  val count = new ObjectProperty(this, "count", _count)
}
