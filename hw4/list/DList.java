/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = newNode(Integer.MIN_VALUE, head, head);
    head.next = head;
    head.prev = head;
    size = 0;
    //  Your solution here.
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    DListNode next = head.next;
    DListNode node = newNode(item, head, next);
    next.prev = node;
    head.next = node;
    size++;
    // Your solution here.
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    DListNode prev = head.prev;
    DListNode node = newNode(item, prev, head);
    prev.next = node;
    head.prev = node;
    size++;
    // Your solution here.
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    return head.next != head ? head.next : null;
    // Your solution here.
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    return head.prev != head ? head.prev : null;
    // Your solution here.
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    if (node == null) return null;
    if (node.next == head) return null;
    return node.next;
    // Your solution here.
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    if (node == null) return null;
    if (node.prev == head) return null;
    return node.prev;
    // Your solution here.
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    if (node == null) return;
    DListNode next = node.next;
    DListNode insertNode = newNode(item, node, next);
    node.next = insertNode;
    next.prev = insertNode;
    size++;
    // Your solution here.
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    if (node == null) return;
    DListNode prev = node.prev;
    DListNode insertNode = newNode(item, prev, node);
    node.prev = insertNode;
    prev.next = insertNode;
    size++;
    // Your solution here.
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    if (node == null) return;
    DListNode prev = node.prev;
    DListNode next = node.next;
    prev.next = next;
    next.prev = prev;
    size--;
    // Your solution here.
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "] size: " + size;
  }

  public static void main(String[] args) {
    DList list = new DList();
    //1
    list.insertFront(1);
    System.out.println(list.toString());
    //1 2
    list.insertBack(2);
    System.out.println(list.toString());
    //1 3 2
    DListNode f = list.front();
    list.insertAfter(3, f);
    System.out.println(list.toString());

    DListNode sec = list.next(f);
    //1 4 3 2
    list.insertBefore(4, sec);
    System.out.println(list.toString());
    //1 4 3 5 2
    list.insertAfter(5, sec);
    System.out.println(list.toString());
    //4 3 5 2
    list.remove(f);
    System.out.println(list.toString());
    //4 3 5
    list.remove(list.back());
    System.out.println(list.toString());
    //3 5
    list.remove(list.front());
    System.out.println(list.toString());
  }
}
