package CUHA.homepage.service.impl;

import CUHA.homepage.configuration.SecurityConfig;
import CUHA.homepage.domain.User;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.UserJoinDto;
import CUHA.homepage.security.dto.UserLoginDto;
import CUHA.homepage.security.jwt.JWTUtil;
import CUHA.homepage.security.jwt.LoginFilter;
import CUHA.homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final SecurityConfig securityConfig;
    @Override
    public String join(UserJoinDto userdto) {
        if(userRepository.findByUsername(userdto.getUsername()).isPresent()){
            throw new UsernameNotFoundException(userdto.getUsername());
        }
        if(userRepository.findByEmail(userdto.getEmail()).isPresent()){
            throw new UsernameNotFoundException(userdto.getEmail());
        }
        User saveUser = User.builder()
                .email(userdto.getEmail())
                .gender(userdto.getGender())
                .password(securityConfig.bCryptPasswordEncoder().encode(userdto.getPassword()))
                .isActive(true)
                .score(0L).build();

        userRepository.save(saveUser);
        return jwtUtil.createJWT(userdto.getUsername(),userdto.getUserRole().toString(),30L);
    }

    @Override
    public String login(UserLoginDto user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            User user1 = userRepository.findByEmail(user.getEmail()).get();
            if(securityConfig.bCryptPasswordEncoder().matches(user.getPassword(), user1.getPassword())){
                return jwtUtil.createJWT(user1.getUsername(),user1.getUserRole().toString(),30L);
            }
            else{
                throw new UsernameNotFoundException(user.getEmail());
            }

        }
        else{
            throw new UsernameNotFoundException(user.getEmail());
        }


    }
}
