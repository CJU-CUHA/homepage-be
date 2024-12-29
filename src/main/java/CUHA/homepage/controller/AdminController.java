package CUHA.homepage.controller;

import CUHA.homepage.security.dto.newsDTO.NewsMessageResponse;
import CUHA.homepage.security.dto.userDTO.UserFindResponse;
import CUHA.homepage.security.dto.userDTO.UserRUDRequest;
import CUHA.homepage.security.dto.userDTO.UserRUDResponse;
import CUHA.homepage.security.dto.userDTO.UserUpdateRequest;
import CUHA.homepage.service.NewsService;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final NewsService newsService;
    @PutMapping("/set/score")
    public ResponseEntity<UserRUDResponse> setScore(@RequestBody UserRUDRequest username, Long score) {
        return ResponseEntity.ok().body(userService.setScore(username, score));
    }

    @PutMapping("/set/updateUser")
    public ResponseEntity<UserRUDResponse> setUserRole(@RequestBody UserUpdateRequest username, HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.updateUserAdmin(request,username));
    }

    @GetMapping("/findusers")
    public ResponseEntity<UserFindResponse> findUsers(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserFindResponse>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/saveGeekNews")
    public ResponseEntity<NewsMessageResponse> saveGeek() throws IOException {
        return ResponseEntity.ok().body(newsService.saveGeekNews());
    }

    @PostMapping("/saveGeekOneNews")
    public ResponseEntity<NewsMessageResponse> saveOneGeek() throws IOException {
        return ResponseEntity.ok().body(newsService.saveGeekOneNews());
    }




//    @PostMapping("/saveBoanNews")
//    public void saveBoan() throws IOException {
//        newsService.saveBoanNews();
//    }

}
