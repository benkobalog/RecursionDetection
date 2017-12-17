object Main extends App {
  val l1 = List(1, 2, 4, 4, 4, 5, 1, 23, 1, 1, 1, 2)
  val l = List(1, 2, 3, 2, 3, 2, 2)
  val res = RecursionDetection.repeatedParts(l)

  val l3 = List(5, 5, 5, 5, 4, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 5)
  val res2 = RecursionDetection.repeatedParts(l3)

  println(res)
  println(res2)

  val charList = "aaaabcccccc".toList

  println(RecursionDetection.repeatedParts(charList))


}
