class Roman {
    static final RomanValue[] ROMAN_VALUES = {
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
        int maxNumber = 0;
        int sum = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            int charValue = romanDigitToArabic(chars[i]);

            if (charValue >= maxNumber) {
                maxNumber = charValue;
                sum += charValue;
            } else {
                sum -= charValue;
            }
        }

        return sum;
    }

    public static int romanDigitToArabic(char digit) throws Exception {
        String romanDigit = String.valueOf(digit);

        for (RomanValue romanValue:ROMAN_VALUES){
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
