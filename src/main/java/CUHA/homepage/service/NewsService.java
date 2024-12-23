package CUHA.homepage.service;

import CUHA.homepage.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    void saveGeekNews() throws IOException;
    void saveBoanNews() throws IOException;
    List<News> findGeekNews();
    Page<News> findGeekNews(int page,int size);
    List<News> findBoanNews();
    Page<News> findBoanNews(int page,int size);
}
