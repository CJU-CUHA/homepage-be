package CUHA.homepage.controller;

import CUHA.homepage.model.Gender;
import CUHA.homepage.security.dto.userDTO.*;
import CUHA.homepage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@RequestBody UserjoinRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userService.addUser(user));
    }
//    @PostMapping("/login")
//    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest user, HttpServletRequest request) {
//
//        UserLoginResponse loginUser = userService.loginUser(user);
//        if (loginUser.isSuccess() == true) {
//            request.getSession().setAttribute("user", user.getUsername());
//            // session이름 jsessionid를 사용함
//            return ResponseEntity.ok().body(loginUser);
//        }
//        else {
//            return ResponseEntity.ok().body(loginUser);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest user, HttpServletRequest request) {
        UserLoginResponse loginUser = userService.loginUser(user, request);
        if (loginUser.isSuccess() == true) {
            request.getSession().setAttribute("user", user.getUsername());
            // session이름 jsessionid를 사용함
            return ResponseEntity.ok().body(loginUser);
        }
        else {
            return ResponseEntity.ok().body(loginUser);
        }
    }

    @PutMapping("/set/userinfo")
    public ResponseEntity<UserRUDResponse> updateuser(@RequestBody UserjoinRequest username){
        username.setPassword(username.getPassword());
        return ResponseEntity.ok().body(userService.updateUser(username));
    }

    @GetMapping("/gender")
    public ResponseEntity<Gender> getGender(@RequestParam String user) {

        return ResponseEntity.ok().body(userService.getGender(user));
    }
    @GetMapping("/score")
    public ResponseEntity<Long> getScore(@RequestParam String user){
        return ResponseEntity.ok().body(userService.getScore(user));
    }
}
