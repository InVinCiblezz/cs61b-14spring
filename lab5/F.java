/* F class */
interface G {
    final static int OCT = 10;
}

public class F extends E implements G{

    public static void main(String[] args) {
        System.out.println(E.OCT);
        System.out.println(G.OCT);
    }

}