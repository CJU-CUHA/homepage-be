package CUHA.homepage.controller;

import CUHA.homepage.domain.User;
import CUHA.homepage.security.dto.UserJoinDto;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<String> join(UserJoinDto userJoinDto) {
        return ResponseEntity.ok().body(userService.join(userJoinDto));
    }
}
