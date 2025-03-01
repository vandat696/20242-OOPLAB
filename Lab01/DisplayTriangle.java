import java.util.Scanner;
public class DisplayTriangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Input n: ");
		int n = scanner.nextInt();
		
		for (int i = 1; i <= n; i++) {
			int star;
				star = 2*i - 1;
			for (int j = n-i; j > 0; j--)
			{
				System.out.print(" ");
			}
			for (int j = 0; j < star; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		scanner.close();
	}
}
