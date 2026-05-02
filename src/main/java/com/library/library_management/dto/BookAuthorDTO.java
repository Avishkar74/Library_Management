package com.library.library_management.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDTO {
    private Long id;
    private String title;
    private String genre;
    private int publishedYear;
    private double price;
    private String authorName;
    private String authorNationality;
}