package com.example.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {
    private Long id;
    private String title;
    private String imagePath;
    private String description;
    private String description2;
    private LocalDateTime publishDate;
    private boolean published;
    private boolean pinned;
    private boolean archived;
}
