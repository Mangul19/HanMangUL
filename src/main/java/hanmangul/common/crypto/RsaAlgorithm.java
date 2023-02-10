package hanmangul.common.crypto;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA 암호화 공통 유틸리티
 */
public class RsaAlgorithm {
    public static final int KEY_SIZE = 1024;

    public static final String RSA_WEB_KEY = "_RSA_WEB_Key_"; // 개인키 session key

    /**
     * 키 생성
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(KEY_SIZE);
        return generator.genKeyPair();
    }

    /**
     * 공개키(string) 조회
     */
    public static String getPublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    /**
     * 개인키(string) 조회
     */
    public static String getPrivateKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }

    /**
     * 암호화
     */
    public static String encrypt(String plainText, String publicKeyStr) throws Exception {
        // 공개키 객체 생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytePublicKey = Base64.getDecoder().decode(publicKeyStr.getBytes());
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return encrypt(plainText, publicKey);
    }

    /**
     * 암호화
     */
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        // 암호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] byteEncryptedData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(byteEncryptedData);
    }

    /**
     * 복호화
     */
    public static String decrypt(String encryptedData, String privateKeyStr) throws Exception {
        // 개인키 객체 생성
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytePrivateKey = Base64.getDecoder().decode(privateKeyStr.getBytes());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return decrypt(encryptedData, privateKey);
    }

    /**
     * 복호화
     */
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        // 복호화
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
        byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
        return new String(byteDecryptedData, StandardCharsets.UTF_8);
    }

    public static void initRsa(HttpServletRequest request) throws Exception {
        KeyPair keyPair = generateKeyPair();
        String publicKeyStr = getPublicKey(keyPair);
        String privateKeyStr = getPrivateKey(keyPair);

        request.getSession().setAttribute(RSA_WEB_KEY, privateKeyStr); // session에 RSA 개인키를 세션에 저장
        request.setAttribute("publicKeyStr", publicKeyStr);
        request.setAttribute("privateKeyStr", publicKeyStr);
    }

    /**
     * 개인키(string) 조회
     */
    public static String getPrivateKey(HttpSession session) {
        if (session.getAttribute(RSA_WEB_KEY) != null) {
            return session.getAttribute(RSA_WEB_KEY).toString();
        }
        return null;
    }
}
