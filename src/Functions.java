//package dabakovich.util;
import java.util.Scanner;

/**
 * Created by David on 29.10.2015.
 * CLASS ABOUT:
 */
public class Functions {
    static Scanner scanner = new Scanner(System.in);

    public static void print(Object s){
        System.out.print(s);
    }
    public static void println(Object s){
        System.out.println(s);
    }

    static String read(){
        return scanner.nextLine();
    }
	static int readInt(){
		int i = 0;
		if (scanner.hasNextInt()) i = scanner.nextInt();
		return i;
	}
	static float readFloat(){
		float f = 0;
		if (scanner.hasNextFloat()) f = scanner.nextFloat();
		return f;
	}
}
