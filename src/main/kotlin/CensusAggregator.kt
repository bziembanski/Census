class CensusAggregator: IAggregator {
    private val nameFactory: FlyweightFactory = FlyweightFactory()
    private val surnameFactory: FlyweightFactory = FlyweightFactory()
    private val personalIdFactory: FlyweightFactory = FlyweightFactory()
    private val nodes: ArrayList<Node<IFlyweight>> = ArrayList()
    private val personalIds: HashSet<IFlyweight> = HashSet()

    override fun find(root: IIterator, content: String): IIterator {
        TODO("Not yet implemented")
    }

    override fun add(name: String, surnames: Pair<String, String>, personalId: String) {
        val personalIdNode = Node(personalIdFactory.getFlyweight(personalId))
        if (personalIdNode.nodeContent in personalIds) throw Throwable("Personal ID already exists")
        personalIds.add(personalIdNode.nodeContent)
        val nameNode = Node(nameFactory.getFlyweight(name))
        val surnameNode = Node(surnameFactory.getFlyweight(surnames.first))
        if(surnames.second!=""){
            val surnameNode2 = Node(surnameFactory.getFlyweight(surnames.second))
            surnameNode2.addChild(personalIdNode)
            surnameNode.addChild(surnameNode2)
        }else{
            surnameNode.addChild(personalIdNode)
        }
        nameNode.addChild(surnameNode)
        nodes.add(nameNode)
    }

    override fun remove(name: String, surnames: Array<String>, personalId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): IIterator {
        return CensusIterator(nodes)
    }
}