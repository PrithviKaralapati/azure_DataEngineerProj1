package typesdemo

// "File" in the New Scala Class/File menu creates a blank .scala file with no template —
// it doesn't force one class/trait/object per file. Scala 3 even allows top-level
// definitions like these, living outside any wrapping type.
def formatReport(lines: Seq[String]): String =
  lines.mkString("--- Vehicle Report ---\n", "\n", "\n----------------------")

val appVersion: String = "1.0.0-demo"

@main def runUtilsDemo(): Unit =
  println(formatReport(Seq("Line 1", "Line 2")))
  println(s"App version: $appVersion")
