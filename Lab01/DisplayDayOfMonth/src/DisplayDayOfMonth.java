import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class DisplayDayOfMonth {
    public static void main(String[] args) {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("january", 1); monthMap.put("jan.", 1); monthMap.put("jan", 1);
        monthMap.put("february", 2); monthMap.put("feb.", 2); monthMap.put("feb", 2);
        monthMap.put("march", 3); monthMap.put("mar.", 3); monthMap.put("mar", 3);
        monthMap.put("april", 4); monthMap.put("apr.", 4); monthMap.put("apr", 4);
        monthMap.put("may", 5);
        monthMap.put("june", 6); monthMap.put("jun.", 6); monthMap.put("jun", 6);
        monthMap.put("july", 7); monthMap.put("jul.", 7); monthMap.put("jul", 7);
        monthMap.put("august", 8); monthMap.put("aug.", 8); monthMap.put("aug", 8);
        monthMap.put("september", 9); monthMap.put("sep.", 9); monthMap.put("sep", 9);
        monthMap.put("october", 10); monthMap.put("oct.", 10); monthMap.put("oct", 10);
        monthMap.put("november", 11); monthMap.put("nov.", 11); monthMap.put("nov", 11);
        monthMap.put("december", 12); monthMap.put("dec.", 12); monthMap.put("dec", 12);

        int month = 0;
        while (month == 0) {
            String input = JOptionPane.showInputDialog("Enter a month (name, abbreviation, or number):").toLowerCase().trim();
            if (monthMap.containsKey(input)) {
                month = monthMap.get(input);
            } else {
                try {
                    int monthNum = Integer.parseInt(input);
                    if (monthNum >= 1 && monthNum <= 12) {
                        month = monthNum;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid month. Please enter again.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid month. Please enter again.");
                }
            }
        }

        int year = -1;
        while (year < 0) {
            try {
                String yearInput = JOptionPane.showInputDialog("Enter a year (4-digit number):").trim();
                if (yearInput.length() == 4 && yearInput.matches("\\d+")) {
                    year = Integer.parseInt(yearInput);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid year. Please enter a 4-digit non-negative number.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid year.");
            }
        }

        int days = getDaysInMonth(month, year);
        JOptionPane.showMessageDialog(null, "Month: " + month + "\nYear: " + year + "\nDays: " + days);
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}