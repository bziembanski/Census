class Node<E>(content: E) {
    private val children: ArrayList<Node<E>> = ArrayList()
    val nodeContent: E = content
    var parent: Node<E>? = null
    fun getChildren(): ArrayList<Node<E>> {
        return children
    }

    fun addChild(child: Node<E>): Boolean{
        return when {
            children.contains(child) -> false
            child==this -> false
            else -> {
                children.add(child)
                child.parent=this
                true
            }
        }
    }

    fun removeChild(child: Node<E>): Boolean{
        return when{
            children.contains(child) ->{
                child.parent=null
                children.remove(child)
            }
            else -> false
        }
    }

    fun removeAllChildren(): Boolean{
        children.forEach{
            removeChild(it)
        }
        return true
    }



}