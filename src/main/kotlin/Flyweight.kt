data class Flyweight(private val flyweightContent: String): IFlyweight{
    override fun getContent(): String {
        return flyweightContent
    }
}