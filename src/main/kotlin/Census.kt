fun main(){
    val censusAggregator = CensusAggregator()
    censusAggregator.add("Bartosz", Pair("Ziembański", "Kowalski"), "98092910055")
    censusAggregator.add("Bartosz", Pair("Ślęzak", ""), "98092910054")
    censusAggregator.add("Adam", Pair("Wkładam","Wykładam"), "00010155511")
    val co = censusAggregator.remove("Bartosz", Pair("Ziembański", "Kowalski"), "98092910055")
    print(co)
}
