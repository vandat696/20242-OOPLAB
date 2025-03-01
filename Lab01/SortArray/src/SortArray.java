import  javax.swing.JOptionPane;
import java.util.Arrays;

public class SortArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = getPositiveInteger("Input n: ");
		
		double arr[] = new double[n];
		for (int i=0; i < n; i++) {
			arr[i] = getDoubleInput("Enter element " + (i+1) + ":");
		}
		
		Arrays.sort(arr);
		
		double sum = 0;
		for (double num : arr) {
			sum += num;
		}
		double average = sum / n;
		
		String result = "Sorted Array: " + Arrays.toString(arr) +
						"\nSum: " + sum +
						"\nAverage: " + average;
		JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static int getPositiveInteger(String message) {
		while (true) {
			try {
				String input = JOptionPane.showInputDialog(message);
				int num = Integer.parseInt(input);
				if (num > 0) {
					return num;
				} else {
					JOptionPane.showMessageDialog(null, "Enter a positive integer!");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input! Try again");
			}
		}
	}
	
	private static double getDoubleInput(String message) {
		while (true) {
			try {
				String input = JOptionPane.showInputDialog(message);
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input! Try Again");
			}
		}
	}
}
