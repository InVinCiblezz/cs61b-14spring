/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue queue = new LinkedQueue();
    int size = q.size();
    for (int i = 0; i < size; ++i) {
      LinkedQueue p = new LinkedQueue();
      try {
        p.enqueue(q.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
      queue.enqueue(p);
    }
    return queue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue q = new LinkedQueue();
    while (q1.size() > 0 && q2.size() > 0) {
      try {
        if (((Comparable) q1.front()).compareTo(q2.front()) <= 0) {
          q.enqueue(q1.dequeue());
        } else {
          q.enqueue(q2.dequeue());
        }
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    while (q1.size() > 0) {
      try {
        q.enqueue(q1.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    while (q2.size() > 0) {
      try {
        q.enqueue(q2.dequeue());
      } catch(QueueEmptyException e) {
        System.err.println(e);
      }
    }
    return q;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    while (qIn.size() > 0) {
      try {
        Object item = qIn.dequeue();
        Comparable c = (Comparable) item;
        if (c.compareTo(pivot) < 0)
          qSmall.enqueue(item);
        else if (c.compareTo(pivot) == 0)
          qEquals.enqueue(item);
        else
          qLarge.enqueue(item);
      } catch (QueueEmptyException e) {
        System.err.println(e);
      }
    }
  }


  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    if (q.size() <= 1) return;
    LinkedQueue queueOfQueues = makeQueueOfQueues(q);
    while (queueOfQueues.size() > 1) {
      try {
        queueOfQueues.enqueue(mergeSortedQueues((LinkedQueue) queueOfQueues.dequeue(), (LinkedQueue) queueOfQueues.dequeue()));
      } catch (QueueEmptyException e) {
        System.err.println(e);
      }
    }
    try {
      q.append((LinkedQueue)queueOfQueues.dequeue());
    } catch(QueueEmptyException e) {
      System.err.println(e);
    }
  }


  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    //base case
    if (q.size() <= 1) return;
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    int rand = getRand(q.size());
    Comparable pivot = (Comparable) q.nth(rand);
    partition(q, pivot, qSmall, qEquals, qLarge);
    quickSort(qSmall);
    quickSort(qLarge);
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
  }

  /**
   *  getRand() gerenate a random number from 1 to max.
   *  @param max is the biggest randome number that can be gerenated.
   *  @return return the gerenated random number.
   **/
  private static int getRand(int max) {
    int min = 1;
    int range = max - min + 1;
    int rand = (int)(Math.random() * range) + min;
    return rand;
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

  }

}
