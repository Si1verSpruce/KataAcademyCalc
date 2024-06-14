class Roman {
    public static final int MAX_EQUAL_NUMBERS_SEQUENCE = 3;

    public static final RomanValue[] ROMAN_VALUES = {
            new RomanValue("C", 100),
            new RomanValue("XC", 90),
            new RomanValue("L", 50),
            new RomanValue("XL", 40),
            new RomanValue("X", 10),
            new RomanValue("IX", 9),
            new RomanValue("V", 5),
            new RomanValue("IV", 4),
            new RomanValue("I", 1)
    };

    public static int romanNumberToArabic(String value) throws Exception {
        char[] chars = value.toCharArray();
        int[] charValues = new int[chars.length];
        int sum = 0;
        int equalSequence = 1;

        for (int i = 0; i < chars.length; i++) {
            int charValue = romanDigitToArabic(chars[i]);

            if (i > 0) {
                if (i > 1) {
                    if (charValue > charValues[i - 2]) {
                        throw new Exception();
                    }
                }

                if (charValue == charValues[i - 1]) {
                    equalSequence++;

                    if (equalSequence > MAX_EQUAL_NUMBERS_SEQUENCE) {
                        throw new Exception();
                    }
                } else {
                    equalSequence = 1;

                    if (charValue > charValues[i - 1]) {
                        if (charValue - charValues[i - 1] == charValues[i - 1]) {
                            throw new Exception();
                        }

                        sum -= charValues[i - 1] * 2;
                    }
                }
            }

            sum += charValue;
            charValues[i] = charValue;
        }

        return sum;
    }

    public static int romanDigitToArabic(char digit) throws Exception {
        String romanDigit = String.valueOf(digit);

        for (RomanValue romanValue : ROMAN_VALUES) {
            if (romanValue.string.equals(romanDigit))
                return romanValue.value;
        }

        throw new Exception();
    }

    public static String arabicNumberToRoman(int arabicNumber) {
        String value = "";
        Number number = new Number(arabicNumber);

        for (RomanValue romanValue : ROMAN_VALUES) {
            value = value.concat(extractRoman(number, romanValue.string, romanValue.value));
        }

        return value;
    }

    public static String extractRoman(Number extractFromNumber, String writableString, int divider) {
        String roman = writableString.repeat(extractFromNumber.value / divider);
        extractFromNumber.value %= divider;
        return roman;
    }

    static class Number {
        int value;

        public Number(int value) {
            this.value = value;
        }
    }

    record RomanValue(String string, int value) {
    }
}
