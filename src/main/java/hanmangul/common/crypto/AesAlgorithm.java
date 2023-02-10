
package hanmangul.common.crypto;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AesAlgorithm {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    /* 0QPaGkPmPF7SafLn */
    public static byte[] ivBytes = { 48, 81, 80, 97, 71, 107, 80, 109, 80, 70, 55, 83, 97, 102, 76, 110 };

    public static String encrypt(String plainText, String key) throws Exception {

        if (StringUtils.isEmpty(plainText)) {
            return plainText;
        }

        // key 생성
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        // 암호화
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
        byte[] bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // encode
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String encrypted, String key) throws Exception {
        if (encrypted == null || encrypted.length() == 0) {
            return encrypted;
        }

        // decode
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);

        // key 생성
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        // 복호화
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
        byte[] bytes = cipher.doFinal(encryptedBytes);

        // byteToString
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
