package lcs

object LongestCommonSubsequence {
  def main(args: Array[String]) {
    println("Hello, world!!!")
  }

  def lcsLazy[T](u: IndexedSeq[T], v: IndexedSeq[T]): IndexedSeq[T] = {
    def su = subsets(u).to(LazyList)

    def sv = subsets(v).to(LazyList)

    su.intersect(sv).headOption match {
      case Some(sub) => sub
      case None => IndexedSeq[T]()
    }
  }

  def subsets[T](u: IndexedSeq[T]): Iterator[IndexedSeq[T]] = {
    u.indices.reverseIterator.flatMap { n => u.indices.combinations(n + 1).map(_.map(u)) }
  }

  def lcsRec[T]: (IndexedSeq[T], IndexedSeq[T]) => IndexedSeq[T] = {
    case (a +: as, b +: bs) if a == b => a +: lcsRec(as, bs)
    case (as, bs) if as.isEmpty || bs.isEmpty => IndexedSeq[T]()
    case (a +: as, b +: bs) =>
      val (s1, s2) = (lcsRec(a +: as, bs), lcsRec(as, b +: bs))
      if (s1.length > s2.length) s1 else s2
  }
}
