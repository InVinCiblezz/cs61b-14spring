/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  DList list;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() {
    list = new DList();
    // Your solution here.
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return list.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if (cardinality() == 0)
      list.insertFront(c);
    else {
      ListNode n = list.front();
      try {
        for (; n.isValidNode(); n = n.next()) {
          if (c.compareTo((Comparable) n.item()) > 0) continue;
          else if (c.compareTo((Comparable) n.item()) == 0) return;
          break;
        }
        if (n.isValidNode()) n.insertBefore(c);
        else list.insertBack(c);
      } catch (InvalidNodeException lbe) {
        System.out.println("insert() should throw an exception, and did.");
      }
    }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    if (s.cardinality() == 0) { // do nothing
      return;
    } else {
      ListNode n = list.front();
      ListNode sn = s.list.front();
      try {
        while (n.isValidNode() && sn.isValidNode()) {
          Comparable i1 = (Comparable) n.item();
          Comparable i2 = (Comparable) sn.item();
          if (i2.compareTo(i1) > 0) {
            n = n.next();
            continue;
          }
          else if (i2.compareTo(i1) == 0) {
            sn = sn.next();
            continue;
          }
          n.insertBefore(sn.item());
          n = n.next();
          sn = sn.next();
        }
        while (sn.isValidNode()) {
          list.insertBack(sn.item());
          sn = sn.next();
        }
      } catch (InvalidNodeException lbe) {
        System.out.println("union() should throw an exception, and did.");
      }
    }
    // Your solution here.
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    if (cardinality() == 0 || s.cardinality() == 0) { // do nothing
      s = new Set();
    } else {
      ListNode n = list.front();
      ListNode sn = s.list.front();
      try {
        while (n.isValidNode() && sn.isValidNode()) {
          Comparable i1 = (Comparable) n.item();
          Comparable i2 = (Comparable) sn.item();
          if (i2.compareTo(i1) == 0) {
            n = n.next();
            sn = sn.next();
            continue;
          } else if (i2.compareTo(i1) > 0) {
            n = n.next();
            n.prev().remove();
            continue;
          }
          sn = sn.next();
        }
        // cut tail
        if (n.isValidNode()) n.remove();
      } catch (InvalidNodeException lbe) {
        System.out.println("intersect() should throw an exception, and did.");
      }
    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    return list.toString();
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);
    System.out.println("s.cardinality() = " + s.cardinality());

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(1));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);
    System.out.println("s.cardinality() = " + s.cardinality());

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    s3.insert(new Integer(1));
    s3.insert(new Integer(4));
    System.out.println("Set s3 = " + s3);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);
    System.out.println("s.cardinality() = " + s.cardinality());

    Set s4 = new Set();
    s4.insert(new Integer(5));
    System.out.println("Set s4 = " + s4);
    s.intersect(s4);
    System.out.println("After s.intersect(s4), s = " + s);
    // You may want to add more (ungraded) test code here.

    /*
    If you use SLists instead of DLists, do your union() and intersect() methods still run within the time bounds?
    If not, how easy would it be to fix them so that they do?
    SList does not have insertBack()
    Fix: keep a prev node to use prev.insertAfter() to replace it.
    */
  }
}
