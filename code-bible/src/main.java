public class main {
    public String reverseString(String iniString) {
        // write code here
        byte[] chars = iniString.getBytes();
        for (int i = 0; i < chars.length/2; i++) {
            byte t = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = t;
        }

        return new String(chars);
    }
    static public void main(String argsp[]) {
        System.out.println("test");
    }
}
