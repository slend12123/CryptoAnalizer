import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        Set<String> dictionary = new HashSet<>(Arrays.asList(
                "the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
                "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
                "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
                "or", "an", "will", "my", "one", "all", "would", "there", "their", "what",
                "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
                "when", "make", "can", "like", "time", "no", "just", "him", "know", "take",
                "people", "into", "year", "your", "good", "some", "could", "them", "see", "other",
                "than", "then", "now", "look", "only", "come", "its", "over", "think", "also",
                "back", "after", "use", "two", "how", "our", "work", "first", "well", "way",
                "even", "new", "want", "because", "any", "these", "give", "day", "most", "us"
        ));

        String bestDecryption = "";
        int bestMatchCount = 0;

        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decrypted = new StringBuilder();

            decrypted.append("Shift " + shift + ": ");

            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    char newChar = (char) ((c - base - shift + 26) % 26 + base);
                    decrypted.append(newChar);
                } else {
                    decrypted.append(c);
                }
            }

            String[] words = decrypted.toString().split("\\W+");
            int matchCount = 0;
            for (String word : words) {
                if (dictionary.contains(word.toLowerCase())) {
                    matchCount++;
                }
            }

            if (matchCount > bestMatchCount) {
                bestMatchCount = matchCount;
                bestDecryption = decrypted.toString();
            }
        }

        return bestDecryption;
    }
}
