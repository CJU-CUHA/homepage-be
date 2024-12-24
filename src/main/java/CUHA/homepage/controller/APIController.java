package CUHA.homepage.controller;

import CUHA.homepage.model.News;
import CUHA.homepage.service.APIService;
import CUHA.homepage.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/api")
@RequiredArgsConstructor
public class APIController {

    private final APIService apiService;
    private final NewsService newsService;
    @GetMapping("ctftime")
    public List<Map<String,String>> ctfTime() throws IOException {
        return apiService.ctfTimeGetApi();
    }

    @GetMapping("geeknews")
    public Page<News> geekNewspage(@RequestParam(value="page", defaultValue="0") int page,
                                   @RequestParam(value="size", defaultValue = "10") int size) throws IOException {
        return newsService.findGeekNews(page,size);
    }




}
