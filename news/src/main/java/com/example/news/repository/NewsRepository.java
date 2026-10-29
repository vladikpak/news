package com.example.news.repository;

import com.example.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
    public interface NewsRepository extends JpaRepository<News, Long> {
        List<News> findByPublishedFalse();

        List<News> findByPinnedTrue();

        List<News> findByArchivedTrue();

        @Query("SELECT n FROM News n WHERE n.published = true AND n.archived = false ORDER BY n.pinned DESC, n.publishDate DESC")
        List<News> findActiveNews();

        @Modifying
        @Transactional
        @Query("UPDATE News n SET n.pinned = false")
        void unpinAll();
}
