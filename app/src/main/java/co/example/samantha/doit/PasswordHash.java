package co.example.samantha.doit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sam Schwartz-Horney on 11/28/2017.
 */

public class PasswordHash {
    private static String hexCode(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte x : data) {
            int half = (x >>> 4) & 0x0F;
            int whole = 0;
            do {
                builder.append((0 <= half) && (half <= 9) ? (char) ('0' + half) : (char) ('a' + (half - 10)));
                half = x & 0x0F;
                whole++;
            } while (whole < 1);
        }
        return builder.toString();
    }

    public static String hashPass(String pass) {
        String hashedPass = "err";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] text = pass.getBytes("UTF-8");
            md.update(text, 0, text.length);
            text = md.digest();
            hashedPass = hexCode(text);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hashedPass;
    }
}
