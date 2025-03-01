import javax.swing.JOptionPane;
public class ChoosingOption {

	public static void main(String[] args) {
		int option = JOptionPane.showOptionDialog(null,
				"Do you want to change to the first class ticket?",
				"Upgrade ticket",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new String[]{"I do", "I don't"},"I do");
		
		JOptionPane.showMessageDialog(null, "You've chosen: "
				+ (option == 0? "I do" : "I don't"));
		System.exit(0);

	}

}
