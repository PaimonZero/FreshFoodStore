package util;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
    public static String BigDecimalToMoney(BigDecimal num) {
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
    
    public static String removeVietnameseAccents(String str) {
        // Chuẩn hóa chuỗi về dạng decomposed (NFD)
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        
        // Sử dụng regex để loại bỏ các ký tự dấu
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }

    public static void main(String[] args) {
        String fullName = "Nguyễn Văn Tài";
        String fullNameWithoutDiacritics = removeVietnameseAccents(fullName);
        
        System.out.println(fullNameWithoutDiacritics);  // Kết quả: Nguyen Van Tai
        
//        String result = Validate.doubleToMoney(190000);
//        System.out.println("Formatted Number: " + result);
    }
}
