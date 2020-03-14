package thirdqopentextfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThirdQOpenTextFile extends Application {

    TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Menu FileMinue = new Menu("_File");

        MenuItem Open = new MenuItem("_Open");
        MenuItem Close = new MenuItem("_Close");
        MenuItem Exit = new MenuItem("E_xit");

        FileMinue.getItems().addAll(Open, Close, Exit);

        MenuBar menuBarFile = new MenuBar();
        menuBarFile.getMenus().add(FileMinue);
        VBox BoxFile = new VBox(menuBarFile);

        Menu EditMinue = new Menu("_Edit");

        MenuItem Font = new MenuItem("_Font");
        MenuItem Color = new MenuItem("_Color");
        EditMinue.getItems().addAll(Font, Color);

        MenuBar menuBarEdit = new MenuBar();
        menuBarEdit.getMenus().add(EditMinue);
        VBox BoxEdit = new VBox(menuBarEdit);

        HBox box = new HBox(BoxFile, BoxEdit);

        textArea = new TextArea();
        textArea.setMinHeight(500);
        VBox vBox = new VBox(box, textArea);

// This The Other Way To Apllid Event Handler ..  
/*
        EventHandler1 handler1 = new EventHandler1();
        Open.setOnAction(handler1);
        Close.setOnAction(handler1);
        Exit.setOnAction(handler1);
        Font.setOnAction(handler1);
        Color.setOnAction(handler1);
         */
        Open.setOnAction(op -> {
            try {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(stage);
                Scanner scanner = new Scanner(selectedFile);
                textArea.setText(" ");
                textArea.setWrapText(true);

                if (selectedFile != null) {
                    while (scanner.hasNextLine()) {
                        textArea.appendText(scanner.nextLine());
                    }
                }
                Open.setDisable(true);
            } catch (FileNotFoundException ex) {
                System.out.println("FileNotFoundException");
            }

        });

        Close.setOnAction(Cl -> {
            textArea.setText(null);
            Open.setDisable(false);
        });

        Exit.setOnAction(EX -> {
            stage.close();
        });

        Font.setOnAction(EX -> {
            ObservableList list = FXCollections.observableArrayList();
            ComboBox box1 = new ComboBox();
            box1.setItems(list);
            list.addAll(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 120, 200);
            ChoiceDialog cd = new ChoiceDialog();
            cd.getItems().addAll(box1.getItems());
            cd.setHeaderText("Resizable .. ");
            cd.setContentText("Select Size To applied in Text Area .. ");
            int s = (int) cd.showAndWait().get();
            textArea.setFont(new Font("Arial", s));
        });

        Color.setOnAction(EX -> {
            ObservableList<String> ol = FXCollections.observableArrayList();
            ol.addAll("red", "blue", "yellow", "black", "green", "brown");
            ChoiceDialog cd = new ChoiceDialog();
            cd.getItems().addAll(ol);
            cd.setSelectedItem(ol.get(0));

            cd.setHeaderText("Color .. ");
            cd.setContentText("Select Color To applied in Text Area .. ");
            Button OK = (Button) cd.getDialogPane().lookupButton(ButtonType.OK);
            OK.setOnAction(ac -> {
                textArea.setStyle("-fx-text-fill:" + cd.getSelectedItem());
            });
            cd.show();

        });
        stage.setTitle("File Txt Format");
        Scene scene = new Scene(vBox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
// Here The Socend Way To Implemintation EventHandler ... 

    /*   private class EventHandler1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String menuItem = ((MenuItem) event.getSource()).getText();

            switch (menuItem) {
                case "_Open":
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Opening File ");
                    fileChooser.setInitialDirectory(new File("."));
                    File f = new FileChooser().showOpenDialog(null);
                    Scanner scanner;
                    textArea.setText(" ");
                    textArea.setWrapText(true);
                    try {
                        scanner = new Scanner(f);
                        while (scanner.hasNext()) {
                            textArea.appendText(scanner.nextLine());
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File Not Found Exception .. ");
                    }
                    break;
                case "_Close":

                    break;

                case "E_xit":

                    break;

                case "_Font":
                    break;

                case "_Color":
                    Dialog<String> dialogColor = new TextInputDialog();
                    dialogColor.setTitle("Color Selection");
                    dialogColor.setHeaderText("Select Color");
                    String color = dialogColor.showAndWait().get();
                    textArea.setStyle("-fx-text-fill:" + color + ";");
                    break;

                default:
                    throw new AssertionError();
            }
        }

    }
     */
}
