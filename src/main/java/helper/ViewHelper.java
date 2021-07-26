package helper;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ViewHelper {
    public static void setGridPaneSelectFile(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(15, 20, 20, 20));

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(80, 80, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(80, 80, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnThreeConstrains = new ColumnConstraints(80, 80, Double.MAX_VALUE);
        columnThreeConstrains.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnFourConstrains = new ColumnConstraints(80, 80, Double.MAX_VALUE);
        columnFourConstrains.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnFiveConstrains = new ColumnConstraints(80, 80, Double.MAX_VALUE);
        columnFiveConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains, columnThreeConstrains, columnFourConstrains, columnFiveConstrains);
    }
    public static void initSearchSection(GridPane gridPane, Button searchButton, int colIndex, int rowindex) {
        HBox searchBox = getHBox();

        searchButton.setPrefWidth(80);

        searchBox.getChildren().addAll(searchButton);
        gridPane.setHalignment(searchBox, HPos.LEFT);
        gridPane.add(searchBox, colIndex, rowindex,1,1);
    }

    public static void RadioButton(GridPane gridPane, RadioButton type1, RadioButton type2, RadioButton type3, RadioButton type4, String labelName,
                                    String promptText, int colIndex, int rowindex) {

        //GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        Label label = new Label(labelName);
        gridPane.setHalignment(label, HPos.LEFT);
        gridPane.add(label, colIndex, rowindex, 4,1);

        HBox searchBox = getHBox();

        final ToggleGroup tg = new ToggleGroup();
        type1.setToggleGroup(tg);
        type2.setToggleGroup(tg);
        type3.setToggleGroup(tg);
        type4.setToggleGroup(tg);

        searchBox.getChildren().addAll( type1,type2,type3,type4);
        gridPane.setHalignment(searchBox, HPos.LEFT);

        gridPane.add(searchBox, colIndex, rowindex+1, 5,1);
    }
    public static Label addLabel(GridPane gridPane, String labelText, int colIndex, int rowIndex) {
        Label label = new Label(labelText);
        gridPane.add(label, colIndex, rowIndex);

        return label;
    }

    public static TextField addTextField(GridPane gridPane, String promptText, int colIndex, int rowIndex) {
        TextField textField = new TextField();
        textField.setPrefHeight(40);
        textField.setPromptText("" + promptText);
        gridPane.add(textField, colIndex, rowIndex);

        return textField;
    }

    public static HBox getHBox() {
        HBox searchBox = new HBox();
        searchBox.setSpacing(10);

        return searchBox;
    }
    public static VBox getVBox() {
        VBox searchBox = new VBox();
        searchBox.setSpacing(10);

        return searchBox;
    }

    public static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase().concat(input.substring(1));
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }
}
