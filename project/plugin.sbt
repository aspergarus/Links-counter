scalacOptions ++= Seq("-unchecked", "-deprecation")

// [https://github.com/sbt/sbteclipse]
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.6")
