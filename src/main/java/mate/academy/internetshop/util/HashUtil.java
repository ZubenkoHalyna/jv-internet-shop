package mate.academy.internetshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import org.apache.log4j.Logger;

public class HashUtil {
    private static Logger LOGGER = Logger.getLogger(HashUtil.class);
    private static String HASH_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            System.out.println(Arrays.toString(salt));
            messageDigest.update(salt);
            return messageDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Enable to get hash with " + HASH_ALGORITHM + " algorithm");
            throw new RuntimeException(e);
        }
    }
}
