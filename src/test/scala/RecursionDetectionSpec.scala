import org.scalatest._
import RecursionDetection._

class RecursionDetectionSpec extends FlatSpec with Matchers {

  "A RecursionDetector" should "return Nil to Nil" in {
    assert(compactRepeats(List[Int]()) == Nil)
  }

  it should "work with Int type" in {
    val intList = List(1, 2, 2, 2, 3, 3, 2, 2)

    assert(
      compactRepeats(intList) == List(RepeatableElement(1, 1),
        RepeatableElement(2, 3),
        RepeatableElement(3, 2),
        RepeatableElement(2, 2)))
  }

  it should "work with Char type" in {
    val charList = "aaaabcccccc".toList

    assert(
      compactRepeats(charList) == List(RepeatableElement('a', 4),
        RepeatableElement('b', 1),
        RepeatableElement('c', 6)))
  }

  it should "work with a class with an equals function" in {
    class A(val a: Int) {
      override def equals(o: Any): Boolean = {
        o match {
          case x: A => x.a == a
          case _ => false
        }
      }
    }

    val list = "11122".toList.map(_.asDigit).map(new A(_))

    assert(
      compactRepeats(list) == List(RepeatableElement(new A(1), 3),
        RepeatableElement(new A(2), 2)))
  }

  it should "not have repetition in lists without repetition" in {
    val charList = "qwertyuiop".toList

    assert(compactRepeats(charList).forall(_.times == 1))
  }

  "indexMap and listFromIndexMap" should "return the original list" in {
    val charList = "aaaabcccccc".toList
    val intList = List(1, 2, 2, 2, 3, 3, 2, 2)


    assert(charList == listFromIndexMap(indexMap(compactRepeats(charList))))
    assert(intList == listFromIndexMap(indexMap(compactRepeats(intList))))
  }
}
