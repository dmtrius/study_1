object FlatList {
  def main(args: Array[String]) {
    println("Hello, world!!!")
    val l = List(List(1), 2, List(List(3, 4), 5), List(List(List())), List(List(List(6))), 7, 8, List())
    println(flatList(l))
  }

  def flatList(l: List[_]): List[Any] = l match {
    case Nil => Nil
    case (head: List[_]) :: tail => flatList(head) ::: flatList(tail)
    case head :: tail => head :: flatList(tail)
  }
}
