import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text, then click enter and enter shift.");
        String result = Cipher.encrypt(scanner.nextLine(), scanner.nextInt());
        System.out.println(result);
        String decryptResult = Cipher.decrypt(result, scanner.nextInt());
        System.out.println(decryptResult);
    }
}
