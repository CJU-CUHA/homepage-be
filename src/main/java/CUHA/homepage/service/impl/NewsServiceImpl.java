package CUHA.homepage.service.impl;

import CUHA.homepage.model.News;
import CUHA.homepage.repository.NewsRepository;
import CUHA.homepage.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public void saveGeekNews() throws IOException {
        String url = "https://news.hada.io/new";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByClass("topic_row");
//        System.out.println(elements);
        Elements linkElements = doc.getElementsByClass("topic_row").select("a[href]");
        for(Element element : elements) {
            String title = element.select("div.topictitle > a > h1").text();
            String content = element.select("div.topicdesc > a").text();
            String saveUrl=element.select("div.topicdesc > a").attr("href");
            newsRepository.save(News.builder()
                    .title(title)
                    .content(content)
                    .url(url.substring(0,url.length()-3)+saveUrl)
                    .boan(false)
                    .build());
        }

    }
    //보류
    @Override
    public void saveBoanNews() throws IOException {
        String url="https://m.boannews.com/html/news.html?mtype=1&tab_type=1";
        // URL 연결 객체 생성
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET"); // GET 요청 (기본 URL 요청)

        // 응답 받기
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 응답된 HTML 파싱
        Document doc = Jsoup.parse(response.toString());
        System.out.println(doc); // 파싱된 HTML 출력

        Elements elements= doc.select("#newsAllContainer li");
        System.out.println(elements);
        for(Element element : elements) {
            String title = element.select("dt").text();
            String content = element.select("a").attr("href");
            String saveUrl=element.select("img").attr("src");
            System.out.println(title);
            System.out.println(content);
            System.out.println(saveUrl);
            newsRepository.save(News.builder()
                    .title(title)
                    .content(content)
                    .url(url+saveUrl)
                    .boan(true)
                    .build());
        }

    }

    @Override
    public List<News> findGeekNews() {
        return newsRepository.findAll();
    }

    @Override
    public Page<News> findGeekNews(int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
        return newsRepository.findAll(pageable);
    }

    @Override
    public List<News> findBoanNews() {
        return List.of();
    }

    @Override
    public Page<News> findBoanNews(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return newsRepository.findAll(pageable);
    }
}
