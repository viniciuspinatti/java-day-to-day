package org.viniciuspinatti.utils;

import java.util.Random;

public class RandomUtils {
  public static String createRandomString() {
    final int minStringLength = 5;
    final int maxStringLength = 12;

    Random random = new Random();

    int length = random.nextInt((maxStringLength - minStringLength) + 1) + minStringLength;

    String allowedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(allowedLetters.length());

      sb.append(allowedLetters.charAt(randomIndex));
    }

    return sb.toString();
  }

  public static int generateRandomNumberBetweenMinAndMax(int min, int max) {
    if (min > max) {
      throw new IllegalArgumentException(
          "The minimum value cannot be greater than the maximum value.");
    }

    Random random = new Random();

    return random.nextInt((max - min) + 1) + min;
  }

  public static float generateRandomNumberBetweenMinAndMax(float min, float max) {
    if (min > max) {
      throw new IllegalArgumentException(
          "The minimum value cannot be greater than the maximum value.");
    }

    Random random = new Random();
    final float generatedFloat = min + (max - min) * random.nextFloat();
    return Math.round(generatedFloat * 100.0f) / 100.0f;
  }
}
