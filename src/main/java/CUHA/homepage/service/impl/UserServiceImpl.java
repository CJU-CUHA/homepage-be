package CUHA.homepage.service.impl;

import CUHA.homepage.domain.User;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.UserJoinDto;
import CUHA.homepage.security.dto.UserLoginDto;
import CUHA.homepage.security.jwt.JWTUtil;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    @Override
    public String join(UserJoinDto userdto) {
        User saveUser = User.builder()
                .email(userdto.getEmail())
                .gender(userdto.getGender())
                .password(userdto.getPassword())
                .isActive(true)
                .score(0L).build();

        userRepository.save(saveUser);
        return jwtUtil.createJWT(userdto.getUsername(),userdto.getUserRole().toString(),30L);
    }

    @Override
    public String login(UserLoginDto user) {
        return jwtUtil.createJWT(user.getUsername(),"",30L);
    }
}
