import java.util.*;
/**
 * @author bianca.gilay
 *
 */
public class Screen {

	public void Screen1A(){
		Scanner getname = new Scanner(System.in);
		System.out.print("								ONLINE MOVIE RESERVATION		\n\n"
				       + "									Welcome!				\n\n\n\n"
		               + "								Please enter your name: ");
		String userName = getname.nextLine();
		getname.close();
		System.out.println("\n\nPress 1 to Continue");
	}
	
	public static void main(String[] args){
		Screen s = new Screen();
		s.Screen1A();
	}
	
}
