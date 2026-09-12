package com.example.news.service;

import com.example.news.dto.NewsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    NewsDto findById(Long id);

    List<NewsDto> listPinned();

    List<NewsDto> listActiveNews();

    List<NewsDto> listDrafts();

    List<NewsDto> listArchived();

    NewsDto OnPinned(Long id);

    NewsDto OffPinned(Long id);

    NewsDto OnPublished(Long id);

    NewsDto OffPublished(Long id);

    NewsDto OnArchive(Long id);

    NewsDto OffArchive(Long id);

    NewsDto updateNews(Long id, NewsDto newsDto, MultipartFile file);

    List<NewsDto> listNews();

    NewsDto createNews(NewsDto newsDto, MultipartFile file);
}
