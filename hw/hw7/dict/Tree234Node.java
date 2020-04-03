/* Tree234Node.java */

package dict;

/**
 *  A Tree234Node is a node in a 2-3-4 tree (Tree234 class).
 *
 *  DO NOT CHANGE ANYTHING IN THIS FILE.
 *  You may add helper methods and additional constructors, though.
 **/
class Tree234Node {

  /**
   *  keys is the number of keys in this node.  Always 1, 2, or 3.
   *  key1 through key3 are the keys of this node.  If keys == 1, the value
   *    of key2 doesn't matter.  If keys < 3, the value of key3 doesn't matter.
   *  parent is this node's parent; null if this is the root.
   *  child1 through child4 are the children of this node.  If this is a leaf
   *    node, they must all be set to null.  If this node has no third and/or
   *    fourth child, child3 and/or child4 must be set to null.
   **/
  int keys;
  int key1;
  int key2;
  int key3;
  Tree234Node parent;
  Tree234Node child1;
  Tree234Node child2;
  Tree234Node child3;
  Tree234Node child4;

  Tree234Node(Tree234Node p, int key) {
    keys = 1;
    key1 = key;
    parent = p;
    child1 = null;
    child2 = null;
    child3 = null;
    child4 = null;
  }

  private void setParent(Tree234Node p) {
    parent = p;
  }

  public Tree234Node reconstuct() {
    if (keys != 3) return null;
    Tree234Node p = parent;
    Tree234Node rightChild;
    //root
    if (p == null) {
      Tree234Node leftChild = new Tree234Node(this, key1);
      rightChild = new Tree234Node(this, key3);
      key1 = key2;
      if (child1 != null) {
        leftChild.child1 = child1;
        child1.setParent(leftChild);
      }
      if (child2 != null) {
        leftChild.child2 = child2;
        child2.setParent(leftChild);
      }
      child1 = leftChild;
      child2 = rightChild;
    } else {
      p.insert(key2);
      rightChild = new Tree234Node(p, key3);
      //parent have 2 keys and 3 kids at most
      //because key2 places in parent, key3 must place in a new node
      if (p.child1 == this) {
        p.child4 = p.child3;
        p.child3 = p.child2;
        p.child2 = rightChild;
      } else if (p.child2 == this) {
        p.child4 = p.child3;
        p.child3 = rightChild;
      } else {
        p.child4 = rightChild;
      }
    }
    if (child3 != null) {
      rightChild.child1 = child3;
      child3.setParent(rightChild);
    }
    if (child4 != null) {
      rightChild.child2 = child4;
      child4.setParent(rightChild);
    }
    child3 = null;
    child4 = null;
    key2 = 0;
    key3 = 0;
    keys = 1;
    //return parent to search again!
    return p != null ? p : this;
  }

  public Tree234Node goDown(int k) {
    if (k <= key1) return child1;
    else if (keys == 1 || k <= key2) return child2;
    else if (keys == 2 || k <= key3) return child3;
    return child4;
  }

  public void insert(int k) {
    if (keys == 3) {
      System.out.println("insert error2");
      return;
    }

    if (keys == 1) {
      if (key1 > k) {
        key2 = key1;
        key1 = k;
      }
      else key2 = k;
    }
    else {
      if (key1 > k) {
        key3 = key2;
        key2 = key1;
        key1 = k;
      } else if (key2 > k) {
        key3 = key2;
        key2 = k;
      }
      else key3 = k;
    }
    keys++;
  }

  public boolean isDuplicate(int k) {
    return (k == key1) || (keys > 1 && k == key2) || (keys == 3 && k == key3);
  }
  public boolean isLeaf() {
    return child1 == null && child2 == null && child3 == null && child4 == null;
  }
  /**
   *  toString() recursively prints this Tree234Node and its descendants as
   *  a String.  Each node is printed in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.
   **/
  public String toString() {
    String s = "";

    if (child1 != null) {
      s = "(" + child1.toString() + ")";
    }
    s = s + key1;
    if (child2 != null) {
      s = s + "(" + child2.toString() + ")";
    } else if (keys > 1) {
      s = s + " ";
    }
    if (keys > 1) {
      s = s + key2;
      if (child3 != null) {
        s = s + "(" + child3.toString() + ")";
      } else if (keys > 2) {
        s = s + " ";
      }
    }
    if (keys > 2) {
      s = s + key3;
      if (child4 != null) {
        s = s + "(" + child4.toString() + ")";
      }
    }
    return s;
  }

  /**
   *  printSubtree() recursively prints this Tree234Node and its descendants as
   *  a tree (albeit sideways).
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printSubtree(int spaces) {
    if (child4 != null) {
      child4.printSubtree(spaces + 5);
    }
    if (keys == 3) {
      for (int i = 0; i < spaces; i++) {
        System.out.print(" ");
      }
      System.out.println(key3);
    }

    if (child3 != null) {
      child3.printSubtree(spaces + 5);
    }
    if (keys > 1) {
      for (int i = 0; i < spaces; i++) {
        System.out.print(" ");
      }
      System.out.println(key2);
    }

    if (child2 != null) {
      child2.printSubtree(spaces + 5);
    }
    for (int i = 0; i < spaces; i++) {
      System.out.print(" ");
    }
    System.out.println(key1);

    if (child1 != null) {
      child1.printSubtree(spaces + 5);
    }
  }
}
