package com.example.news.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "images")
    private String images;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "description2", nullable = false)
    private String description2;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Builder.Default
    @Column(name = "published")
    private boolean published = false;

    @Builder.Default
    @Column(name = "pinned")
    private boolean pinned = false;

    @Builder.Default
    @Column(name = "archived")
    private boolean archived = false;
}
