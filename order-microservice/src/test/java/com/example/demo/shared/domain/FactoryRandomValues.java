package com.example.demo.shared.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

public class FactoryRandomValues {

    public static String generateRandomAlphanumeric(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        return generateRandomString(8) + "@" + generateRandomString(5) + ".com";
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomPhoneNumber() {
        return "+1" + (int) (Math.random() * 1000000000);
    }

    public static int generateRandomIntValue(int start, int end){
        return (int) (Math.random()*(end-start)  + start);
    }

    public static int generateRandomIntValue(int limit){
        return (int) (Math.random()*limit);
    }

    public static Long generateRandomLongValue(int limit){
        return (long) (Math.random() * limit);
    }
    public static <T extends Enum<T>> T getRandomEnumValue(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    public static BigDecimal generateRandomBigDecimal(BigDecimal max) {
        BigDecimal random = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble());
        return random.multiply(max);
    }

    public static OffsetDateTime randomOffsetDateTime() {
        long minEpoch = Instant.parse("2000-01-01T00:00:00Z").toEpochMilli();
        long maxEpoch = Instant.now().toEpochMilli();

        long randomEpoch = ThreadLocalRandom.current()
                .nextLong(minEpoch, maxEpoch);

        return OffsetDateTime.ofInstant(
                Instant.ofEpochMilli(randomEpoch),
                ZoneOffset.UTC
        );
    }
}
