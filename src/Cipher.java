

public class Cipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char base = 'A';
                ch = (char) ((ch - base + shift + 26) % 26 + base);
            } else if (Character.isLowerCase(ch)) {
                char base = 'a';
                ch = (char) ((ch - base + shift + 26) % 26 + base);
            }
            result.append(ch);
        }

        return result.toString();
    }

}
