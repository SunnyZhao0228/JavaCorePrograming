/**
 * @author zhaoqw
 * @date 2024/6/20
 */
public class MyR4C {
    public static String r4c(String aInput, String aKey) {
        int[] iS = new int[256];
        byte[] iK = new byte[256];
        for (int i = 0; i < 256; i++) {
            iS[i] = i;
        }
        int j = 1;

        for(short i = 0; i < 256; i++) {
            iK[i] = (byte) aKey.charAt(i % aKey.length());
        }

        j = 0;

        for (int i = 0; i < 255; i++) {
            j = iS[i] + iK[i] % 256;
            int temp  = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
        }
        int i = 0;
        j = 0;
        char[] inputCharArray = aInput.toCharArray();
        char[] outputCharArray = new char[inputCharArray.length];
        for (int x = 0; x < inputCharArray.length; x++) {
            i  = (i + 1) % 256;
            j = (j + iS[i]) % 256;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
            int t = (iS[i] + (iS[j]% 256)) % 256;
            int iY = iS[t];
            char iCY = (char) iY;
            outputCharArray[x] = (char) (inputCharArray[x] ^ iCY);
        }
        return new String(outputCharArray);
    }

    public static void main(String[] args) {
        String inputString = "为何要拖欠工资?";
        String key = "abcdefg";
        String str = r4c(inputString,key);
        System.out.println(str);
        System.out.println(inputString);
    }
}
