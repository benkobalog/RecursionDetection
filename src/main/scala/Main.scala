object Main extends App {
  val res = RecursionDetection.compactRepeats(List(1, 2, 4, 4, 4, 5, 1, 23, 1, 1, 1, 2))

  res.foreach(println)

  val res2 = RecursionDetection.indexMap(res)

  println(res2)

  println(RecursionDetection.listFromIndexMap(res2))

}
