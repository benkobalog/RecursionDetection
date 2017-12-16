import scala.annotation.tailrec

object RecursionDetection {
  case class RepeatableElement[A](a: A, times: Int) {
    require(times > 0)
  }

  type CompactList[A] = List[RepeatableElement[A]]

  def compactRepeats[A](list: List[A]): CompactList[A] = {
    @tailrec
    def loop(remainder: List[A], acc: CompactList[A]): CompactList[A] =
      remainder match {
        case Nil => acc
        case x :: xs =>
          if (acc.nonEmpty && x == acc.head.a)
            loop(xs, RepeatableElement(x, acc.head.times + 1) :: acc.tail)
          else
            loop(xs, RepeatableElement(x, 1) :: acc)
      }
    loop(list, Nil).reverse
  }

  case class IndexAndTimes(idx: Int, times: Int)

  def indexMap[A](xs: CompactList[A]): Map[A, List[IndexAndTimes]] =
    xs.zipWithIndex
      .groupBy(_._1.a)
      .mapValues(_.map(x => IndexAndTimes(x._2, x._1.times)))

  def listFromIndexMap[A](idxMap: Map[A, List[IndexAndTimes]],
                          fromIdx: Int = 0): List[A] = {
    idxMap.find(x => x._2.exists(_.idx == fromIdx)) match {
      case Some(x) =>
        val times = x._2.find(_.idx == fromIdx).get.times
        val updatedList = x._2.filterNot(_ == IndexAndTimes(fromIdx, times))
        val updatedMap = idxMap + (x._1 -> updatedList)
        List.fill(times)(x._1) ::: listFromIndexMap(updatedMap, fromIdx + 1)
      case None =>
        List[A]()
    }
  }

}
