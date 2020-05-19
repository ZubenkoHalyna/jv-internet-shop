package mate.academy.internetshop.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(salt);
            return toHexString(messageDigest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Enable to get hash with " + HASH_ALGORITHM + " algorithm");
            throw new RuntimeException(e);
        }
    }

    public static String hashPassword(String password, String salt) {
        return hashPassword(password, fromHexString(salt));
    }

    public static String toHexString(byte[] bytes) {
        return new BigInteger(bytes).toString(16);
    }

    public static byte[] fromHexString(String str) {
        return new BigInteger(str, 16).toByteArray();
    }
}
