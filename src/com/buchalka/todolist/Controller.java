package com.buchalka.todolist;

import com.buchalka.todolist.datamodel.TodoItem;
import com.sun.deploy.security.SelectableSecurityManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;


import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birdhay card",
                "Buy a 30th birthday card for Jong",
                LocalDate.of(2020, Month.DECEMBER, 25));
        TodoItem item2 = new TodoItem("Car repair appoitment",
                "Repairer Zivota at 123 Maint street. Bring paperwork",
                LocalDate.of(2020, Month.DECEMBER, 23));
        TodoItem item3 = new TodoItem("Finish design proposal for client",
                "I promised Mike I'd email webiste mocups by Friday 22. April",
                LocalDate.of(2020, Month.DECEMBER, 22));
        TodoItem item4 = new TodoItem("Pickup Dout at the train station",
                "Doug's arriving on March 23 on the five o clock train",
                LocalDate.of(2020, Month.NOVEMBER, 23));
        TodoItem item5 = new TodoItem("Pick up dry cleaning",
                "The clohtes should be ready by Wednesday",
                LocalDate.of(2021, Month.FEBRUARY, 20));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d.M.yyyy");
                    deadlineLabel.setText(df.format(item.getDeadLine()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadLine().toString());
//        System.out.println("Selected item is " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due ");
//        sb.append(item.getDeadLine().toString());
//        itemDetailsTextArea.setText(sb.toString());
//        itemDetailsTextArea.setText(item.getDetails());
    }
}
