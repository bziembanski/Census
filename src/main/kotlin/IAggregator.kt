interface IAggregator {
    fun find(root: IIterator, content: String): IIterator
    fun add(name: String, surnames: Pair<String, String>, personalId: String)
    fun remove(name: String, surnames: Array<String>, personalId: String): Boolean
    fun iterator(): IIterator
}
