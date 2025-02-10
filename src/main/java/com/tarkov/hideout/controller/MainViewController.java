package com.tarkov.hideout.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.web.reactive.function.client.WebClient;
import com.tarkov.hideout.model.Item;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewController
{
    @FXML private TableView<Item> itemTable;

    @FXML private TableColumn<Item, String> nameColumn;

    @FXML private TableColumn<Item, Integer> quantityColumn;

    @FXML private TextField itemNameField;

    @FXML private TextField itemQuantityField;

    private final WebClient webClient = WebClient.create("http://localhost:8080/api/items");

    private final ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        nameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        quantityColumn.setCellValueFactory(cellData -> {
            Integer quantity = cellData.getValue().getQuantity();
            return new SimpleIntegerProperty(quantity != null ? quantity : 0).asObject();
        });
        itemTable.setItems(items);
        loadItems();
        System.out.println("FXML загружен!");
    }

    private void loadItems()
    {
        webClient.get()
                .retrieve()
                .bodyToMono(Item[].class)
                .subscribe(response ->
                {
                    List<Item> itemList = Arrays.asList(response);
                    items.setAll(itemList);
                });
    }

    @FXML
    public void addItem()
    {
        String name = itemNameField.getText();
        String quantityText = itemQuantityField.getText();

        if (name.isEmpty() || quantityText.isEmpty())
        {
            showAlert("Ошибка", "Заполните все поля!");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите число в поле количества!");
            return;
        }

        Item nItem = new Item(name, quantity);

        webClient.post()
                .bodyValue(nItem)
                .retrieve()
                .bodyToMono(Item.class)
                .subscribe(response ->
                {
                    items.add(response);
                    itemNameField.clear();
                    itemQuantityField.clear();
                });
    }

    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}