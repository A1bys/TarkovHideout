package com.tarkov.hideout.model;

import jakarta.persistence.*;

// Определяет сущность JPA для таблицы "items"
@Entity
@Table(name = "items")
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкрементное поле ID
    private Long id;

    @Column(nullable = false, unique = true) // Поле name должно быть уникальным и не может быть null
    private String name;

    @Column(nullable = false) // Поле quantity не может быть null
    private int quantity;

    // Конструктор без параметров (необходим для JPA)
    public Item() {}

    // Конструктор с параметрами
    public Item(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры для доступа к полям класса
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}