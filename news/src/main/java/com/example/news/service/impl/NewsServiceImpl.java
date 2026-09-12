package com.example.news.service.impl;

import com.example.news.dto.NewsDto;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final FileService fileService;

    @Override
    public NewsDto findById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        return convertToDto(news);
    }

    @Override
    public List<NewsDto> listPinned() {
        return newsRepository.findByPinnedTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> listActiveNews() {
        return newsRepository.findActiveNews().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> listDrafts() {
        return newsRepository.findByPublishedFalse().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> listArchived() {
        return newsRepository.findByArchivedTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto OnPinned(Long id){
        newsRepository.unpinAll();

        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setPinned(true);

        newsRepository.save(news);

        return convertToDto(news);
    }

    @Override
    public NewsDto OffPinned(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setPinned(false);

        return convertToDto(news);
    }

    @Override
    public NewsDto OnPublished(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setPublished(true);

        return convertToDto(news);
    }

    @Override
    public NewsDto OffPublished(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setPublished(false);

        return convertToDto(news);
    }

    @Override
    public NewsDto OnArchive(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setArchived(true);

        return convertToDto(news);
    }

    @Override
    public NewsDto OffArchive(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        news.setArchived(false);

        return convertToDto(news);
    }

    @Override
    public NewsDto updateNews(Long id, NewsDto newsDto, MultipartFile file) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("news not found"));

        if (newsDto.getTitle() != null) {
            news.setTitle(newsDto.getTitle());
        }

        if (newsDto.getDescription() != null) {
            news.setDescription(newsDto.getDescription());
        }
        if (newsDto.getDescription2() != null) {
            news.setDescription2(newsDto.getDescription2());
        }

        if (file != null && !file.isEmpty()) {
            String imagePath = fileService.saveImage(file);
            news.setImages(imagePath);
        }

        news.setPublishDate(newsDto.getPublishDate());

        newsRepository.save(news);

        return newsDto;
    }

    @Override
    public List<NewsDto> listNews() {
        return newsRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto createNews(NewsDto newsDto, MultipartFile file) {
        String image = fileService.saveImage(file);

        News news = News.builder()
                .title(newsDto.getTitle())
                .images(image)
                .description(newsDto.getDescription())
                .description2(newsDto.getDescription2())
                .publishDate(LocalDateTime.now())
                .archived(false)
                .pinned(false)
                .published(false)
                .build();

        News savedNews = newsRepository.save(news);

        return convertToDto(savedNews);
    }

    public NewsDto convertToDto(News news) {
       return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .imagePath(news.getImages())
                .description2(news.getDescription2())
                .publishDate(news.getPublishDate())
                .archived(news.isArchived())
                .pinned(news.isPinned())
                .published(news.isPublished())
                .build();
    }
}
