object RecursionDetection {

  def repeatedParts[A](list: List[A]) = {
    val maxPossibleRepetitionLength = list.length / 2

    (1 to maxPossibleRepetitionLength)
      .map { repetitionLength =>
        list.zipWithIndex
          .sliding(repetitionLength * 2)
          .filter { x =>
            val (left, right) = x.splitAt(repetitionLength)
            left.map(_._1) == right.map(_._1)
          }
          .flatten
          .toSet
          .toList
          .sortWith(_._2 < _._2)
          .map(_._1)
      }
      .filterNot(_.length == 0)
      .distinct
  }

}
