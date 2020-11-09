interface IIterator {
    fun hasNext(): Boolean
    fun next(): Node<IFlyweight>?
    fun lower(): IIterator
}