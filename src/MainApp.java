import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainApp extends Application {

    private File inputFile;
    private File outputFile;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Caesar Cipher");

        Label label = new Label("Enter shift (1 - 26) \n       | \n      \\/");

        TextField shiftField = new TextField();
        shiftField.setMaxWidth(50);

        Button chooseFileButton = new Button("Choose file");

        TextArea chosenInputFile = new TextArea();
        chosenInputFile.setMaxSize(400, 2);
        chosenInputFile.setEditable(false);

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");
        Button bruteForceButton = new Button("Brute force");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);


        Button saveFileButton = new Button("Choose output file");


        chooseFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            inputFile = fileChooser.showOpenDialog(primaryStage);
            if (inputFile != null) {
                chosenInputFile.setText("Chosen file: " + inputFile.getAbsolutePath());
            }
        });

        encryptButton.setOnAction(event -> {
            try {
                int shift = Integer.parseInt(shiftField.getText());
                String text = Files.readString(inputFile.toPath());
                String result = Cipher.encrypt(text, shift);
                outputArea.setText(result);

            } catch (IOException e) {
                outputArea.setText("You must choose a file");
            } catch (NumberFormatException e) {
                outputArea.setText("Shift must be a number");
            }
        });

        decryptButton.setOnAction(event -> {
            try {
                int shift = Integer.parseInt(shiftField.getText());
                String text = Files.readString(inputFile.toPath());
                String result = Cipher.decrypt(text, shift);
                outputArea.setText(result);
            } catch (IOException e) {
                outputArea.setText("You must choose a file");
            } catch (NumberFormatException e){
                outputArea.setText("Shift must be a number");
            }
        });

        bruteForceButton.setOnAction(event -> {
            try {
                String text = Files.readString(inputFile.toPath());
                String result = Cipher.bruteForce(text);
                outputArea.setText(result);
            } catch (IOException e) {
                outputArea.setText("You must choose a file");
            } catch (NumberFormatException e){
                outputArea.setText("Shift must be a number");
            }
        });

        saveFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.setInitialFileName("result.txt");
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                try {
                    String text = outputArea.getText();
                    Files.write(Path.of(file.getAbsolutePath()), text.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                label,
                shiftField,
                chooseFileButton,
                chosenInputFile,
                encryptButton,
                decryptButton,
                bruteForceButton,
                outputArea,
                saveFileButton
        );

        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
