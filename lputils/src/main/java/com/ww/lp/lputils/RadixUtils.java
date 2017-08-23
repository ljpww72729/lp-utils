package com.ww.lp.lputils;

/**
 * Created by LinkedME06 on 21/08/2017.
 */

public class RadixUtils {

    /**
     * 将字体的16*16点阵int数组转化为byte数组，请不要用于其他地方，防止数据丢失
     */
    public static byte[] ints2BytesForFonts(int[] ints) {
        byte[] bytes = new byte[ints.length];
        for (int i = 0; i < ints.length; i++) {
            bytes[i] = (byte) ints[i];
        }
        return bytes;
    }

    /**
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] bytes) {
        String hexString = "";

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString += hex.toUpperCase();
        }

        return hexString;
    }

    /**
     * 将字节数组转成2进制字符串，包含前缀0，例如0000 0001
     */
    public static String bytes2BinaryString(byte[] bytes) {
        String binaryString = "";
        for (int i = 0; i < bytes.length; i++) {
            //以下逻辑是为了获取前缀包含0的二进制字符串，需要先将字节转成int类型在^0x100，
            // 这样可以保证是例如1 0000 0001这样的格式，再截取字符串即可
            String binary = Integer.toBinaryString((bytes[i] & 0xFF) ^ 0x100);
            binaryString += binary.substring(1, binary.length());
        }
        return binaryString;
    }

    /**
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串转字节数组
     * 参考：http://www.cnblogs.com/mstk/p/5579841.html
     */
    public static byte[] hexString2Bytes(String hex) {
        // WARNING: 21/08/2017 lipeng 该方法未验证
        if ((hex == null) || (hex.equals(""))) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }

    }

}
