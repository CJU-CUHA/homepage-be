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
import org.springframework.data.domain.Page;
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

    @PutMapping("/set/updateuser")
    public ResponseEntity<UserRUDResponse> setUserRole(@RequestBody UserUpdateRequest username, HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.updateUserAdmin(request,username));
    }

    @GetMapping("/findusers")
    public ResponseEntity<UserFindResponse> findUsers(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }
    @GetMapping("/getusers")
    public ResponseEntity<Page<UserFindResponse>> getUsers(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(userService.getUsers(page,size));
    }

    @PostMapping("/savegeeknews")
    public ResponseEntity<NewsMessageResponse> saveGeek() throws IOException {
        return ResponseEntity.ok().body(newsService.saveGeekNews());
    }

    @PostMapping("/savegeekonenews")
    public ResponseEntity<NewsMessageResponse> saveOneGeek() throws IOException {
        return ResponseEntity.ok().body(newsService.saveGeekOneNews());
    }




//    @PostMapping("/saveBoanNews")
//    public void saveBoan() throws IOException {
//        newsService.saveBoanNews();
//    }

}
