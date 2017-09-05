package com.example.sprinter.item;


public class Item {

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private String endDate;
    private String type;
    private String status;

    public Item() {

    }

    public Item(Long id, String name, String description, Long userId, String endDate, String type, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }
}

