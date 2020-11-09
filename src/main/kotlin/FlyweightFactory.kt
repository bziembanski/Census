class FlyweightFactory{
    private val flyweights = HashSet<IFlyweight>()

    fun getFlyweight(content: String): IFlyweight {
        var index = flyweights.indexOfFirst{ it.getContent() == content }
        if (index == -1){
            flyweights.add(Flyweight(content))
            index = flyweights.indexOfFirst { it.getContent() == content }
        }
        return flyweights.elementAt(index)
    }

    fun removeFlyweight(content: String): Boolean {
        return flyweights.removeIf { it.getContent() == content }
    }
}