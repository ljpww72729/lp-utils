package com.ww.lp.lputils;

import org.junit.Test;

/**
 * Created by LinkedME06 on 21/08/2017.
 */
public class RadixUtilsTest {

    private int[] liData = new int[]{0x1, 0x0, 0x1, 0x8, 0x7f, 0xfc, 0x1, 0x0, 0x5, 0x40, 0x9, 0x30, 0x31, 0xe, 0xc1, 0x4, 0xf, 0xe0, 0x0, 0x40, 0x1, 0x88, 0x7f, 0xfc, 0x1, 0x0, 0x1, 0x0, 0x5, 0x0, 0x2, 0x0};
    private int[] pengData = new int[]{0x2, 0x4, 0x3f, 0x7e, 0x22, 0x44, 0x22, 0x44, 0x22, 0x44, 0x3e, 0x7c, 0x22, 0x44, 0x22, 0x44, 0x22, 0x44, 0x3e, 0x7c, 0x22, 0x44, 0x22, 0x44, 0x22, 0x44, 0x4a, 0x84, 0x44, 0x94, 0x81, 0x8};
    private int[] testData = new int[]{0xff, 0xff};

    @Test
    public void ints2BytesForFonts() throws Exception {
        byte[] liBytes = RadixUtils.ints2BytesForFonts(liData);
        System.out.println(RadixUtils.bytes2HexString(liBytes));
    }

    @Test
    public void bytes2HexString() throws Exception {

    }

    @Test
    public void hexString2BinaryString() throws Exception {
        byte[] liBytes = RadixUtils.ints2BytesForFonts(liData);
        String binaryString = RadixUtils.bytes2BinaryString(liBytes);
        System.out.println(binaryString);
        for (int i = 0; i < binaryString.length(); i++) {
            int binaryInt = Character.digit(binaryString.charAt(i), 2);
            if (binaryInt == 1) {
                System.out.print("**");
            } else {
                System.out.print("  ");
            }
            if ((i + 1) % 16 == 0) {
                System.out.println();
            }
        }
    }
}