import javax.swing.event.InternalFrameListener

class CensusAggregator: IAggregator {
    private val nameFactory: FlyweightFactory = FlyweightFactory()
    private val surnameFactory: FlyweightFactory = FlyweightFactory()
    private val personalIdFactory: FlyweightFactory = FlyweightFactory()
    private val nodes: ArrayList<Node<IFlyweight>> = ArrayList()
    private val personalIds: HashSet<IFlyweight> = HashSet()

    override fun find(root: IIterator, content: String): Node<IFlyweight>? {
        while(root.hasNext()){
            val node = root.next()
            if(node?.nodeContent?.getContent()==content){
                return node
            }
        }
        return null
    }

    override fun add(name: String, surnames: Pair<String, String>, personalId: String) {
        var nameNode: Node<IFlyweight>? = null
        var surnameNode: Node<IFlyweight>? = null
        var surnameNode2: Node<IFlyweight>? = null
        val personalIdNode = Node(personalIdFactory.getFlyweight(personalId))
        if (personalIdNode.nodeContent in personalIds) throw Throwable("Personal ID already exists")
        personalIds.add(personalIdNode.nodeContent)
        nodes.forEach {
            if(name==it.nodeContent.getContent())
                nameNode = it
        }
        if (nameNode == null)
            nameNode = Node(nameFactory.getFlyweight(name))
        nameNode?.getChildren()?.forEach {
            if(surnames.first==it.nodeContent.getContent())
                surnameNode=it
        }
        if (surnameNode == null)
            surnameNode = Node(surnameFactory.getFlyweight(surnames.first))
        if(surnames.second!=""){
            surnameNode?.getChildren()?.forEach {
                if(surnames.second==it.nodeContent.getContent())
                    surnameNode2=it
            }
            if(surnameNode2==null)
                surnameNode2 = Node(surnameFactory.getFlyweight(surnames.second))
            surnameNode2?.addChild(personalIdNode)
            surnameNode?.addChild(surnameNode2!!)
        }else{
            surnameNode?.addChild(personalIdNode)
        }
        if(!nameNode?.getChildren()?.contains(surnameNode!!)!!)
            nameNode!!.addChild(surnameNode!!)
        if(nameNode !in nodes)
            nodes.add(nameNode!!)
    }

    override fun remove(name: String, surnames: Pair<String, String>, personalId: String): Boolean {
        val rootIt = iterator(nodes)
        //val node = rootIt.next()
        //println(node?.nodeContent?.getContent())
        val nameNode = find(rootIt, name) ?: return false
        val surnameNode1 = find(CensusIterator(nameNode.getChildren()),surnames.first)?: return false
        if(surnames.second!=""){
            val surnameNode2 = find(CensusIterator(surnameNode1.getChildren()), surnames.second) ?: return false
            val personalIdNode = find(CensusIterator(surnameNode2.getChildren()), personalId)?: return false
            personalIdNode.removeAllChildren()
            surnameNode2.removeChild(personalIdNode)
            if(surnameNode2.getChildren().size==0)
                surnameNode1.removeChild(surnameNode2)
            if(surnameNode1.getChildren().size==0)
                nameNode.removeChild(surnameNode1)
            if(nameNode.getChildren().size==0)
                nodes.remove(nameNode)
        }else{
            val personalIdNode = find(CensusIterator(surnameNode1.getChildren()), personalId)?: return false
            personalIdNode.removeAllChildren()
            surnameNode1.removeChild(personalIdNode)
            if(surnameNode1.getChildren().size==0)
                nameNode.removeChild(surnameNode1)
            if(nameNode.getChildren().size==0)
                nodes.remove(nameNode)
        }
        return true

    }

    override fun iterator(nodesArrayList: ArrayList<Node<IFlyweight>>): IIterator {
        return CensusIterator(nodesArrayList)
    }
}