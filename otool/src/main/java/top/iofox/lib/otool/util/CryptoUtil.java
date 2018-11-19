package top.iofox.lib.otool.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @See https://developer.android.google.cn/guide/topics/security/cryptography
 */
public class CryptoUtil {

    public final static String ALGORITHM_AES = "AES/CBC/PKCS5PADDING"; //Set your own here.
    private final static String HEX = "0123456789abcdef";
    private static final String SHA1PRNG = "SHA1PRNG";

    public static byte[] encryptAes(byte[] key, byte[] data) {
        return cipher(key, data, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decryptAes(byte[] key, byte[] data) {
        return cipher(key, data, Cipher.DECRYPT_MODE);
    }

    public static class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        public CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

    public static byte[] cipher(byte[] key, byte[] data, int opmode) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(key);
            keygen.init(256, sr);
            SecretKey secretKey = keygen.generateKey();
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES/CBC/PKCS5PADDING");
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            if (ALGORITHM_AES.contains("ECB")) {
                cipher.init(opmode, secretKey); //ECB模式不需要偏移量
            } else {
                cipher.init(opmode, keySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            }
            return cipher.doFinal(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] digest(byte[] message, String type) {
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            return md.digest(message);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] sign(byte[] message) {
        try {
            PrivateKey key = getPrivateKey("oliver");
            Signature s = Signature.getInstance("SHA256withECDSA");
            s.initSign(key);
            s.update(message);
            return s.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verify(byte[] key, byte[] message, byte[] signature) {
        try {
            PublicKey publicKey = getPublicKey(key);
            Signature s = Signature.getInstance("SHA256withECDSA");
            s.initVerify(publicKey);
            s.update(message);
            return s.verify(signature);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static PublicKey getPublicKey(byte[] key) throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuilder result = new StringBuilder(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            result.append(HEX.charAt((buf[i] >> 4) & 0x0f)).append(HEX.charAt(buf[i] & 0x0f));
        }
        return result.toString();
    }

    public static String md5(String message) {
        byte[] result = digest(message.getBytes(Charset.defaultCharset()), "MD5");
        return toHex(result);
    }


}
