package controller;

import entity.EmployeesProjectEntity;
import entity.FileEntity;
import helper.DateAllParse;
import helper.ViewHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.apache.commons.lang.time.DateUtils;
import javafx.scene.layout.GridPane;
import view.ChooseFileView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class FileController {

    private static ObservableList<EmployeesProjectEntity> fileObservableList;
    private static Map<Integer, EmployeesProjectEntity> map = new HashMap<Integer, EmployeesProjectEntity>();
    private static String labelMaxWorkDays;


    public static ObservableList<EmployeesProjectEntity> getTextFileInfo(File isPeopleSearched, String criteria) {
        try {
            Collection<FileEntity> list = Files.readAllLines(isPeopleSearched.toPath())
                    .stream()
                    .map(line -> {
                        String[] details = line.split(",");

                        FileEntity cd = new FileEntity();
                        cd.setEmpId(Integer.valueOf(details[0].trim()));
                        cd.setProjectId(Integer.valueOf(details[1].trim()));
                        cd.setDateFrom(DateAllParse.parseDate(details[2].trim()));
                        cd.setDateTo(DateAllParse.parseDate(details[3].trim()));

                        if (cd.getDateFrom().after(cd.getDateTo())){
                            ChooseFileView.show();
                            //System.exit(0);
                        }

                        return cd;
                    })
                    .collect(Collectors.toList());
            MapListEmployeesProject(list);
            fileObservableList = FXCollections.observableArrayList(map.values());
            MaxDays();
            //fileObservableList.filtered()
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileObservableList;
    }

    public static Map<Integer, EmployeesProjectEntity> MapListEmployeesProject(Collection<FileEntity> list) {
        for (FileEntity fe : list) {
            EmployeesProjectEntity cd = new EmployeesProjectEntity();
            Date dateFrom;
            Date dateTo;

            cd.setProjectID(fe.getProjectId());
            cd.setEmployeeID1(fe.getEmpId());

            for (FileEntity e : list) {
                if (e.getEmpId() != fe.getEmpId() && e.getProjectId() == fe.getProjectId()) {
                    cd.setEmployeeID2(e.getEmpId());
                    if (e.getDateFrom().before(fe.getDateFrom())) {
                        cd.setDateFrom(e.getDateFrom());
                        dateFrom = e.getDateFrom();
                    } else {
                        cd.setDateFrom(fe.getDateFrom());
                        dateFrom = fe.getDateFrom();
                    }
                    if (e.getDateTo().before(fe.getDateTo())) {
                        cd.setDateTo(e.getDateTo());
                        dateTo = e.getDateTo();
                    } else {
                        cd.setDateTo(fe.getDateTo());
                        dateTo = fe.getDateTo();
                    }
                    int days = 0;

                    while (!dateFrom.equals(dateTo)) {
                        days++;
                        dateFrom = DateUtils.addDays(dateFrom, 1);
                    }
                    cd.setDays(days);
                }
            }
            map.put(cd.getProjectID(), cd);
        }

       return map;
    }

    public static String MaxDays(){
        Integer maxDays = 0;
        Integer emp1 = null;
        Integer emp2 = null;
        Integer projectID = null;
        for (EmployeesProjectEntity fe : fileObservableList){
            if(fe.getDays()>maxDays){
                maxDays = fe.getDays();
                emp1 = fe.getEmployeeID1();
                emp2 = fe.getEmployeeID2();
                projectID = fe.getProjectID();
            }
        }
        labelMaxWorkDays = String.format("По проект с id:%dи хора в екип с номера: %d %dса работили най-дълго време заедно! %d дни", projectID, emp1, emp2, maxDays);
        ChooseFileView.labelView(labelMaxWorkDays);
        return labelMaxWorkDays;

    }
}
