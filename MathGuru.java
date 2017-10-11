
import java.util.Scanner;


public class MathGuru {
public static void main(String[] args) {
	boolean flag = true;
	Scanner scan = new Scanner(System.in);
	do{
		System.out.println("********************************************************************************");
		System.out.println("1:2^n, with input n. For example, when n is 3, the output is 8");
		System.out.println("2:n!, with input n. For example, 5! = 5*4*3*2*1");
		System.out.println("3:fab(n) with input n. fab(n) is the nth number in the Fibonacci sequence, with 1, 1, 2, 3, 5, 8, 13, 21, 34");
		System.out.println("Please input the number before functions to selcect a function");
		System.out.println("*********************************************************************************");
		String str = scan.nextLine().toUpperCase();
	if(str.contains("1")){
		System.out.println("Please input the value of 2^n");
	}else if(str.contains("2")){
		System.out.println("Please input the value of n!");
	}else if(str.contains("3")){
		System.out.println("Please input the value of fab(n)");
		int n = Integer.parseInt(scan.nextLine());
		while(!judge(n)){
			System.out.println("Please input the value of fab(n)");
			n = Integer.parseInt(scan.nextLine());
		}
		System.out.println("The last number of fab("+n+") is "+fab(n));
	}
	System.out.println();
	System.out.println("Please input yes if you want to try again, or input no to leave");
	str = scan.nextLine().toUpperCase();
	if(str.equals("NO")){
		flag=false;
	}
	}while(flag);
	System.out.println("The program is closed");
}	
	//function 3 Fibonacci sequence
	public static int fab(int n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fab(n - 1) + fab(n - 2); 
		}
	}
	public static boolean judge(int n){
		if(n > 20){
			System.out.println("Please select a value below 20");
			return false;
		}else{
			return true;
		}
	}
}