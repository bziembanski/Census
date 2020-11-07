class CensusIterator(private val nodes: ArrayList<Node<IFlyweight>>): IIterator {
    private var index: Int = 0
    override fun hasNext(): Boolean {
        return index<nodes.size-1
    }

    override fun next(): Node<IFlyweight>? {
        return if(this.hasNext())
            nodes[index++]
        else
            null
    }
}