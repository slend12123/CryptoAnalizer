
public class Cipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        try {
            for (char ch : text.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    char base = 'A';
                    ch = (char) ((ch - base - shift + 26) % 26 + base);
                } else if (Character.isLowerCase(ch)) {
                    char base = 'a';
                    ch = (char) ((ch - base - shift + 26) % 26 + base);
                }
                result.append(ch);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return result.toString();
    }

    public static String bruteForce(String text) {
        StringBuilder result = new StringBuilder();
        try {
            int j = 1;
            for (int i = 1; i <= 26; i++) {

                result.append(j + ". ");
                j++;
                for (char ch : text.toCharArray()) {

                    if (Character.isUpperCase(ch)) {
                        char base = 'A';
                        ch = (char) ((ch - base - i + 26) % 26 + base);

                    } else if (Character.isLowerCase(ch)) {

                        char base = 'a';
                        ch = (char) ((ch - base - i + 26) % 26 + base);
                    }
                    result.append(ch);
                }
                result.append("\n");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return result.toString();
    }
}
