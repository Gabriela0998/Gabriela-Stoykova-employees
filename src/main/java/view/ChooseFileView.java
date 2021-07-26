package view;

import com.sun.corba.se.spi.orb.ParserData;
import controller.FileController;
import entity.EmployeesProjectEntity;
import entity.FileEntity;
import helper.DateAllParse;
import helper.ViewHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


import org.apache.commons.lang.time.DateUtils;

public class ChooseFileView {

    private TableView<EmployeesProjectEntity> fileTableView;
    Stage subStage;
    static Label textLabel;
    static GridPane gridPane;

    public ChooseFileView() {
        subStage = new Stage();
        subStage.setTitle("Избор на файл");
        Scene scene = new Scene(createPeopleCreateFormPane(), 1000, 900);
        subStage.setScene(scene);
        subStage.show();
    }

    public GridPane createPeopleCreateFormPane() {
        gridPane = new GridPane();

        ViewHelper.setGridPaneSelectFile(gridPane);
        addUIControls(gridPane);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        //View
        Label headerLabel = new Label("Двойка служители, които най-дълго време са работили в екип");
        headerLabel.setFont(new Font("Arial", 26));
        gridPane.add(headerLabel, 0, 0, 4, 1);

        //RadioButton
        RadioButton t1 = new RadioButton("Всички");
        RadioButton t2 = new RadioButton("txt");
        RadioButton t3 = new RadioButton("pdf");
        RadioButton t4 = new RadioButton("doc");
        t1.setSelected(true);
        ViewHelper.RadioButton(gridPane, t1, t2, t3, t4, "Избор формат текстов документ", "", 0, 1);

        //Button for choose file
        Button selectButton = new Button("Избор");
        ViewHelper.initSearchSection(gridPane, selectButton, 4, 1);

        //Label
        textLabel = new Label("");
        gridPane.add(textLabel, 0, 3, 5, 2);

        //Action
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
                , new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
                , new FileChooser.ExtensionFilter("DOC Files", "*.doc")
        );

        selectButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(subStage);
            if (selectedFile != null) {
                gridPane.add(getTableView(FileController.getTextFileInfo(selectedFile, null), gridPane), 0, 4, 5, 50);

            }
        });

        t1.setOnAction(event -> {
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt")
                    , new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
                    , new FileChooser.ExtensionFilter("DOC Files", "*.doc")
            );
        });
        t2.setOnAction(event -> {
                fileChooser.getExtensionFilters().clear();
                fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
                );
        });
        t3.setOnAction(event -> {
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
            );
        });
        t4.setOnAction(event -> {
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("DOC Files", "*.doc")
            );
        });

    }

    private TableView getTableView(ObservableList<EmployeesProjectEntity> observableList, GridPane gridPane) {
        //Column for empID
        TableColumn<EmployeesProjectEntity, Integer> empID = new TableColumn<>("EmpID#1");
        empID.setMinWidth(100);
        empID.setCellValueFactory(new PropertyValueFactory<>("employeeID1"));

        //Column for empID
        TableColumn<EmployeesProjectEntity, Integer> empID2 = new TableColumn<>("EmpID#2");
        empID2.setMinWidth(100);
        empID2.setCellValueFactory(new PropertyValueFactory<>("employeeID2"));

        //Column for last projectID
        TableColumn<EmployeesProjectEntity, Integer> projectID = new TableColumn<>("ProjectID");
        projectID.setMinWidth(80);
        projectID.setCellValueFactory(new PropertyValueFactory<>("projectID"));

        //Column for dateFrom
        TableColumn<EmployeesProjectEntity, Date> dateFrom = new TableColumn<>("DateFrom");
        dateFrom.setMinWidth(220);
        dateFrom.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));

        //Column for dateFrom
        TableColumn<EmployeesProjectEntity, Date> days = new TableColumn<>("Days");
        days.setMinWidth(100);
        days.setCellValueFactory(new PropertyValueFactory<>("days"));
        days.setSortType(TableColumn.SortType.ASCENDING);
        days.sortTypeProperty().setValue(TableColumn.SortType.ASCENDING);
        days.sortableProperty();

        //Column for date of dateToColumn
        TableColumn<EmployeesProjectEntity, Date> dateToColumn = new TableColumn<>("DateTo");
        dateToColumn.setMinWidth(220);
        dateToColumn.setCellValueFactory(new PropertyValueFactory<>("dateTo"));


        fileTableView = new TableView<>();
        fileTableView.setEditable(true);
        fileTableView.setItems(observableList);
        fileTableView.getColumns().addAll(projectID, empID, empID2, dateFrom, dateToColumn, days);

        return fileTableView;
    }

    public static void labelView(String labelMaxWorkDays){
        textLabel = new Label(labelMaxWorkDays);
        textLabel.setFont(new Font("Arial", 20));
        gridPane.setHalignment(textLabel, HPos.LEFT);
        gridPane.add(textLabel, 0, 3, 5, 1);
    }

    public static void show(){
        ViewHelper.showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                "Form Error!", "Грешни данни! Датата от започване на проекта заедно е по-голяма от датата на приключване!");
        return;
    }
}

