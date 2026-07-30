package com.example.news.controller;

import com.example.news.dto.NewsDto;
import com.example.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public String listNews(@RequestParam(required = false) String filter, Model model) {
        List<NewsDto> news;

        if (filter == null || filter.isEmpty()) {
            news = newsService.listNews();  // все новости
        } else {
            switch (filter) {
                case "published":
                    news = newsService.listActiveNews();  // только опубликованные
                    break;
                case "drafts":
                    news = newsService.listDrafts();      // только черновики
                    break;
                case "archive":
                    news = newsService.listArchived();    // только архив
                    break;
                case "pinned":
                    news = newsService.listPinned();      // только закрепленные
                    break;
                default:
                    news = newsService.listNews();        // все новости
            }
        }
        model.addAttribute("news", news);
        model.addAttribute("filters", filter);
        return "news/list";
    }

    @PostMapping("/{id}/togglePinned")
    public String togglePinned(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        if (news.isPinned()) {
            newsService.OffPinned(id);
        } else {
            newsService.OnPinned(id);
        }
        return "redirect:/news";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("news", new NewsDto());
        return "news/create";
    }

    @PostMapping("/{id}/togglePublished")
    public String togglePublished(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        if (news.isPublished()) {
            newsService.OffPublished(id);
        } else {
            newsService.OnPublished(id);
        }
        return "redirect:/news";
    }

    @PostMapping("/{id}/toggleArchive")
    public String toggleArchive(@PathVariable Long id) {
        NewsDto news = newsService.findById(id);
        if (news.isArchived()) {
            newsService.OffArchive(id);
        } else {
            newsService.OnArchive(id);
        }
        return "redirect:/news";
    }

    @PostMapping("/create")
    public String createNews(@ModelAttribute NewsDto newsDto, @RequestParam("photo") MultipartFile photo) {
        newsService.createNews(newsDto, photo);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String editNews(@PathVariable Long id, Model model) {
        NewsDto news = newsService.findById(id);
        model.addAttribute("news", news);
        return "news/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute NewsDto newsDto, @PathVariable Long id, @RequestParam(value = "imageFile", required = false) MultipartFile file) {
        newsService.updateNews(id, newsDto, file);
        return "redirect:/news";
    }

    @GetMapping("{id}")
    public String viewNews(Model model, @PathVariable Long id) {
        NewsDto newsDto = newsService.findById(id);
        model.addAttribute("news", newsDto);
        return "news/view";
    }
}
