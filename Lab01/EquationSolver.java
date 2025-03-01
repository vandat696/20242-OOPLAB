import javax.swing.JOptionPane;

public class EquationSolver {
    public static void main(String[] args) {
        String[] options = {
            "Giai phuong trinh bac nhat 1 an",
            "Giai he phuong trinh bac nhat 2 an",
            "Giai phuong trinh bac 2"
        };

        int choice = JOptionPane.showOptionDialog(null,
            "Chon loai phuong trinh muon giai:",
            "Equation solver",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);

        switch (choice) {
            case 0: 
                solveLinearEquation();
                break;
            case 1:
                solveLinearSystem();
                break;
            case 2:
                solveQuadraticEquation();
                break;
            default: 
                System.exit(0);
        }
    }

    private static void solveLinearEquation() {
        double a, b;
        a = getDoubleInput("Input a");
        if (a == 0) {
            b = getDoubleInput("Input b");
            if (b == 0) {
            JOptionPane.showMessageDialog(null, "Phuong trinh co vo so nghiem","Result", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            }
            if (b != 0) {
                JOptionPane.showMessageDialog(null, "Phuong trinh vo nghiem", "Result", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
        b = getDoubleInput("Input b");
        double x = -b/a;
        JOptionPane.showMessageDialog(null, "Solution: x = " + x, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void solveLinearSystem() {
        double a11, a12, a21, a22, b1, b2;
        a11 = getDoubleInput("Input a11:");
        a12 = getDoubleInput("Input a12:");
        b1 = getDoubleInput("Input b1:");
        a21 = getDoubleInput("Input a21:");
        a22 = getDoubleInput("Input a22:");
        b2 = getDoubleInput("Input b2:");

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0)
            JOptionPane.showMessageDialog(null, "Phuong trinh co vo so nghiem", "Result", JOptionPane.INFORMATION_MESSAGE);
            else {
                JOptionPane.showMessageDialog(null, "Phuong trinh vo nghiem", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            double x1 = D1/D;
            double x2 = D2/D;
            JOptionPane.showMessageDialog(null, "Solution: x1 = " + x1 + ",x2 = " + x2, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void solveQuadraticEquation() {
        double a, b, c;
        a = getDoubleInput("Input a: (a != 0)");
        while (a == 0) {
            JOptionPane.showMessageDialog(null, "Hay nhap a khac 0", "Error", JOptionPane.ERROR_MESSAGE);
            a = getDoubleInput("Input a:");
        }
        b = getDoubleInput("Input b:");
        c = getDoubleInput("Input c:");

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            JOptionPane.showMessageDialog(null, "Hai nghiem la: x1 = " + x1 + ", x2 = " + x2, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "Nghiem kep: x = " + x, "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Phuong trinh vo nghiem", "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message, "Input", JOptionPane.QUESTION_MESSAGE);
                if (input == null || input.trim().isEmpty()) throw new NumberFormatException();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
