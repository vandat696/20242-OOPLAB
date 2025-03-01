import javax.swing.JOptionPane;
public class CaculatorSimulator {
    public static void main(String[] args){
        String strNum1, strNum2;
        String strNotification = "You've just entered: ";
        double Sum;
        double Difference;
        double Product;
        double Quotient;

        strNum1 = JOptionPane.showInputDialog(null,
                "Please input the first number: ","Input the first number",
                JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + " and ";

        strNum2 = JOptionPane.showInputDialog(null,
                "Please input the second number: ","Input the second number",
                JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2;

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
        Sum = num1 + num2;
        Difference = num1 - num2;
        Product = num1 * num2;
        if (num2 != 0) {
                Quotient = num1 / num2;
            } else {
                Quotient = Double.NaN;
            }

        strNotification += "\nSum = " + Sum  + 
                           "\nDifference = " + Difference +
                           "\nProduct = " + Product;
        if (!Double.isNaN(Quotient)) {
                strNotification += "\nQuotient = " + Quotient;
        } else {
                strNotification += "\nQuotient = Undefined (Cannot divide by zero)";
        }
        JOptionPane.showMessageDialog(null,strNotification,
                "Result ", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
    }
}