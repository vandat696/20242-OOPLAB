import javax.swing.JOptionPane;

public class AddTwoMatrix {
    public static void main(String[] args) {
        // Getting the size of the matrices from the user
        int rows = getPositiveInteger("Enter the number of rows: ");
        int cols = getPositiveInteger("Enter the number of columns: ");

        // Initializing two matrices
        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        int[][] resultMatrix = new int[rows][cols];

        // Input values for the first matrix
        JOptionPane.showMessageDialog(null, "Enter values for the first matrix:");
        fillMatrix(matrix1, rows, cols);

        // Input values for the second matrix
        JOptionPane.showMessageDialog(null, "Enter values for the second matrix:");
        fillMatrix(matrix2, rows, cols);

        // Adding the matrices
        addMatrices(matrix1, matrix2, resultMatrix, rows, cols);

        // Displaying the result
        displayMatrix(resultMatrix, "Resulting Matrix (Matrix 1 + Matrix 2):");
    }

    // Method to get a positive integer from the user
    private static int getPositiveInteger(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(message);
                int num = Integer.parseInt(input);
                if (num > 0) {
                    return num;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive integer!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please try again.");
            }
        }
    }

    // Method to fill a matrix with user input
    private static void fillMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Enter element at position [" + i + "][" + j + "]:"));
            }
        }
    }

    // Method to add two matrices
    private static void addMatrices(int[][] matrix1, int[][] matrix2, int[][] resultMatrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }

    // Method to display a matrix
    private static void displayMatrix(int[][] matrix, String message) {
        StringBuilder result = new StringBuilder(message + "\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result.append(matrix[i][j]).append("  ");
            }
            result.append("\n");
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }
}
