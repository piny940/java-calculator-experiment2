package ch.idsia.utils;

public class Binary {
  public static int[] encode(int number) {
    String binaryString = Integer.toBinaryString(number);
    int[] result = new int[binaryString.length()];

    for (int i = 0; i < binaryString.length(); i++) {
      result[i] = Integer.valueOf(binaryString.charAt(i));
    }
    return result;
  }
}
