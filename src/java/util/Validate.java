package util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Validate {

    public static String doubleToMoney(double num) {
        String result = null;
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
            result = numberFormat.format(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static double moneyToDouble(String money) {
        double result = 0;
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
            result = numberFormat.parse(money).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertDateFormat(Date originalDate) {
        String formattedDate = null;
        try {
            // Định dạng ngày ban đầu
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd");
            // Định dạng ngày mới
            SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Chuyển đổi từ chuỗi ngày thành Date
//            Date originalDate = originalFormat.parse(dateStr);
            // Định dạng lại thành chuỗi với định dạng mới
            formattedDate = newFormat.format(originalDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static void main(String[] args) {
        String result = Validate.doubleToMoney(190000);
        System.out.println("Formatted Number: " + result);
    }
}
