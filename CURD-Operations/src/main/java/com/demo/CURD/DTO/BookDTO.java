package com.demo.CURD.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotNull
    private   Integer bookId;
    @Min(value = 0, message = "Book Price should not be less than 0")
    @Max(value = 1000, message = "Book Price should not be more than 0")
    private Double price;
    private String name;
    private String author;
    @Size(min = 4, max = 80, message = "Description should be short")
    private String description;
}
