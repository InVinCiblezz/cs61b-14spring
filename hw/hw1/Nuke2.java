import java.io.*;

class Nuke2 {
    public static void main(String[] arg) throws Exception{
        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter a string: ");
        System.out.flush();        /* Make sure the line is printed immediately. */
        inputLine = keyboard.readLine();

        for(int i = 1; i < inputLine.length(); i ++)
            System.out.print(inputLine.charAt(i));

    }
}