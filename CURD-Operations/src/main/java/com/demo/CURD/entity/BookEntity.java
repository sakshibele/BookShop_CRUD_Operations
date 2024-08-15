package com.demo.CURD.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="book")
public class BookEntity {
    @Id
    private Integer bookId;

    private Double price;
    private String name;
    private String author;
    private String description;

}
