package com.field.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * The {@code DecodeUtils} class provides utility methods for decoding strings.
 * <p>This class includes methods to decode Base64-encoded strings into their original form.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class DecodeUtils {

    // Private constructor to prevent instantiation
    private DecodeUtils() {}

    /**
     * Decodes a Base64-encoded string into its original form.
     * 
     * @param encodedString the Base64-encoded string to be decoded.
     * @return the decoded string.
     */
    public static String getDecodedString(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) {
            return encodedString;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
