/* LockDList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList {

    public void lockNode(DListNode node) {
        if (node != null) ((LockDListNode) node).isLocked = true;
    }

    protected LockDListNode newNode(Object item, DListNode prev, DListNode next){
        return new LockDListNode(item, prev, next);
    }

    public void remove(DListNode node) {
        if (((LockDListNode) node).isLocked == false) super.remove(node);
    }

    public LockDListNode front(){
        return (LockDListNode)(super.front());
    }

    public LockDListNode back(){
        return (LockDListNode)(super.back());
    }

    public LockDListNode next(DListNode node){
        return (LockDListNode)(super.next(node));
    }

    public LockDListNode prev(DListNode node){
        return (LockDListNode)(super.prev(node));
    }

    public static void main(String[] args) {
        LockDList list = new LockDList();
        //1
        list.insertFront(1);
        System.out.println(list.toString());
        //1 2
        list.insertBack(2);
        System.out.println(list.toString());
        //2
        list.remove(list.front());
        System.out.println(list.toString());
        //test lock
        list.lockNode(list.front());
        list.remove(list.front());
        System.out.println(list.toString());
    }
}