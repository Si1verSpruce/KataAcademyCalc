import java.util.Scanner;

public class Main {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 10;

    public static void main(String[] args) throws Exception {
        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);

        while (isActive) {
            System.out.print("Введите операцию: ");
            String inputLine = scanner.nextLine();

            if (inputLine.equals("STOP")) {
                isActive = false;
            } else {
                String result = calc(inputLine);
                System.out.println("Результат операции: " + result);
                System.out.println();
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] values = input.split(" ");
        boolean isArabic = checkForArabic(values[0]);

        if (values.length != 3) {
            throw new Exception();
        }

        if (isArabic != checkForArabic(values[2])) {
            throw new Exception();
        }

        int firstNumber;
        int secondNumber;

        if (isArabic) {
            firstNumber = Integer.parseInt(values[0]);
            secondNumber = Integer.parseInt(values[2]);
        } else {
            firstNumber = Roman.romanNumberToArabic(values[0]);
            secondNumber = Roman.romanNumberToArabic(values[2]);
        }

        tryForPermissibleRange(firstNumber);
        tryForPermissibleRange(secondNumber);
        int result = getResult(firstNumber, secondNumber, values[1].charAt(0));

        if (isArabic) {
            return String.valueOf(result);
        } else if (result > MIN_NUMBER) {
            return Roman.arabicNumberToRoman(result);
        } else {
            throw new Exception();
        }
    }

    public static boolean checkForArabic(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void tryForPermissibleRange(int value) throws Exception {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new Exception();
        }
    }

    public static int getResult(int firstNumber, int secondNumber, char mathOperation) throws Exception {
        return switch (mathOperation) {
            case '+' -> firstNumber + secondNumber;
            case '-' -> firstNumber - secondNumber;
            case '*' -> firstNumber * secondNumber;
            case '/' -> firstNumber / secondNumber;
            default -> throw new Exception();
        };
    }
}