package hanmangul.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HmacUtils {
    private static final String SIGNATURE_ALGORITHM = "HmacSHA256";

    public static boolean verifyHash(String message, String secretKey, String authHash) {
        boolean result;
        try {
            result = createHash(message, secretKey).equals(authHash);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static String createHash(String message, String secretKey) throws Exception {
        if (StringUtils.isEmpty(message)) {
            throw new IllegalArgumentException("Empty message");
        }

        // key 생성
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SIGNATURE_ALGORITHM);

        // hash 값 계산
        Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);
        mac.init(secretKeySpec);
        byte[] bytes = mac.doFinal(message.getBytes());

        // encoding
        return Base64.getEncoder().encodeToString(bytes);
    }
}
