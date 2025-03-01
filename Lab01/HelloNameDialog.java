import javax.swing.JOptionPane;

public class HelloNameDialog {
   public static void main(String[] var0) {
    String result;
      result = JOptionPane.showInputDialog("please enter your name:");
      JOptionPane.showMessageDialog(null, "Hi " + result + "!");
      System.exit(0);
   }
}
