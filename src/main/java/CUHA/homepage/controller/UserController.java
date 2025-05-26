package CUHA.homepage.controller;

import CUHA.homepage.domain.User;
import CUHA.homepage.security.dto.UserJoinDto;
import CUHA.homepage.security.dto.UserLoginDto;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    @PostMapping("/api/user/join")
    public ResponseEntity<String> join(UserJoinDto userJoinDto) {
        try{
            return ResponseEntity.ok().body(userService.join(userJoinDto));
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("already exists");
        }
    }
    @PostMapping("/api/user/login")
    public ResponseEntity<String> login(UserLoginDto userLoginDto) {
        try{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userService.login(userLoginDto));
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not exists");
        }
    }
}
