package hanmangul.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class KeyUtils {
    private static final Integer AES_256 = 32;

    public static String createSecretKey() {
        return RandomStringUtils.randomAlphanumeric(AES_256);
    }

    public static String createSecretKey(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

}
