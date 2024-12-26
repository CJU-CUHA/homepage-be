package CUHA.homepage.repository;

import CUHA.homepage.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);
    Page<News> findAllByBoanFalse(Pageable pageable);
    boolean existsByTitle(String title);

}
