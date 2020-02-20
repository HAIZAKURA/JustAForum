package run.nya.justaforum.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String plainText) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] procBytes = plainText.getBytes();
            byte[] digest = md5.digest(procBytes);
            char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6',
                    '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuffer procSB = new StringBuffer();
            for (byte eachByte : digest) {
                procSB.append(chars[(eachByte >> 4) & 15]);
                procSB.append(chars[eachByte & 15]);
            }
            String res = procSB.toString();
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
