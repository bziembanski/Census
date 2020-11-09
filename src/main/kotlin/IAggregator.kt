interface IAggregator {
    fun find(root: IIterator, content: String): Node<IFlyweight>?
    fun add(name: String, surnames: Pair<String, String>, personalId: String)
    fun remove(name: String, surnames: Pair<String, String>, personalId: String): Boolean
    fun iterator(nodesArrayList: ArrayList<Node<IFlyweight>>): IIterator?
}
