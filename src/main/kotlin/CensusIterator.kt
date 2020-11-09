class CensusIterator(private val nodes: ArrayList<Node<IFlyweight>>): IIterator {
    private var it= nodes.listIterator()
    override fun hasNext(): Boolean {
        return it.hasNext()
    }

    override fun next(): Node<IFlyweight>? {
        return if (hasNext())
            it.next()
        else null
    }

    override fun lower(): IIterator{
        val iter = CensusIterator(it.next().getChildren())
        it.previous()
        return iter
    }
}