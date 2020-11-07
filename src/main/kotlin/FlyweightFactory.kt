class FlyweightFactory{
    private val flyweights = HashSet<IFlyweight>()

    fun getFlyweight(content: String): IFlyweight {
        var index = flyweights.indexOfFirst{ it.getContent() == content }
        if (index == -1){
            println("jeszcze nie istnieje")
            flyweights.add(Flyweight(content))
            index = flyweights.indexOfFirst { it.getContent() == content }
        }
        else println("już istnieje")
        return flyweights.elementAt(index)
    }

    fun removeFlyweight(content: String): Boolean {
        println("usuwam jeśli istnieje")
        return flyweights.removeIf { it.getContent() == content }
    }
}