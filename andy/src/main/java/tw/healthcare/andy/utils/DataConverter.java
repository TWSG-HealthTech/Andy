package tw.healthcare.andy.utils;

public class DataConverter {
    public static String toTwoDecimalPlacesString(Double number) {
        if(number == null) {
            return "";
        } else {
            return String.format("%1$.2f", number);
        }
    }

    public static double toDouble(String numberText) {
        try {
            return Double.parseDouble(numberText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
