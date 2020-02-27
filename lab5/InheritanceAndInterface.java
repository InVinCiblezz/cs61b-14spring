/* inheritance and java interface*/

public class InheritanceAndInterface {
    /*
    * What about arrays of objects? Suppose xa is an array of X’s, and ya is an array of Y’s.
    * (a) At compile-time, can we assign xa to ya, and vice versa? When is a cast required?
    * Answer: Superclass need cast to assign to subclass at complier-time/ Subclass could be assigned to superclass
    * (b) At run-time, if ya references an array of Y’s, can we assign it to xa? Can we then assign it back from xa to ya?
    * Answer: At run-time, we can assign it to xa/ We cannot assign it back from xa to ya.
    * (c) If xa references an array of X’s (that are not Y’s), can we assign it to ya? Can we then assign it back from ya to xa?
    * Answer: We need to cast it to Y[] at complier-time. However, assigning subclass to superclass will occur ClassCastException at run-time.
    * Does it make a difference if the array of type X[] references objects that are all of class Y?
    * Answer: It can pass at run-time. Beacuse it is Y[] actually.
    * Why do you think this is the case?
    */
    public static void Part1() {
        X [] xa = new X[3];
        Y [] ya = new Y[3];
        //c1
        //ya = (Y[]) xa;
        //xa = ya;
        //c2
        //xa = ya;
        //ya = (Y[]) xa;
    }

    /*
    * Suppose a subclass inherits a method implementation from a superclass, and implements a Java interface
    * (that’s the "interface" keyword) that contains a method with the same name and prototype.
    * (a)  Will Java compile the result?
    * Answer: YES
    * (b)  What if the method declaration in the interface has a different return type?
    * Answer: YES
    * (c)  What if the method declaration in the interface has the same return type, but a signature with a different parameter type?
    * Answer: YES
    * (d)  What if the method declaration in the interface has the same return type, and the same number of parameters and parameter types,
    * but those parameters have different names?
    * Answer: YES
    */
    public static void Part2() {
        B b = new B();
    }

    /*
    * Suppose a subclass inherits a "public static final" constant from a superclass,
    * and implements a Java interface that contains a "public static final" constant with the same name.
    * (a)  Will Java compile the result?  Does it make any difference whether the
    * constant in the superclass and the constant in the interface have the same value?
    * Answer: YES. No difference.
    * (b) Write a main() method in the subclass that accesses the constant using the same name used in the superclass and the Java interface.
    * Will Java compile the result? Does it make any difference whether the constant in the superclass and the constant in the interface have the same value?
    * Answer: Compile-time error. No difference.
    * (c) Figure out how to modify your main() method so that it accesses and prints one of the two conflicting values.
    * (Look to the Lecture 9 notes for clues.) Make sure your code compiles and runs without errors.
    * Answer: System.out.println(E.OCT); System.out.println(G.OCT);
    * */

    public static void Part3() {
        F f = new F();
    }
    /*
    * Consider a subclass that has a method that overrides a method with the same prototype in its superclass.
    * (a) Define a variable whose static type is the subclass and which references an object of the subclass.
    * If we cast the variable to the superclass type before calling the overridden method
    *           ((Superclass) subclassvariable).method();
    * does Java call the superclass method or the subclass method?
    * Answer: subclass method.
    * (b) Define a variable whose static type is the superclass and which references
    * an object of the superclass (but not the subclass). If we cast the variable to the subclass type before calling the method,
    * does Java call the superclass method or the subclass method?
    * Answer: Run-time error.
    * (c) Suppose you have an object whose class is the subclass.
    * Can you figure out a way to call the superclass method on that object without having to go through the subclass method of the same name?
    * Answer: super.method()
    */
    public static void Part4() {
        //a
        //K k = new K();
        //((J) k).OverridenMethod();
        //b
        //J j = new J();
        //((K) j).OverridenMethod();
        //c
        K k = new K();
        k.OverridenMethodSuper();
    }
    public static void main(String[] args) {
        Part1();
        Part2();
        Part3();
        Part4();
    }
}