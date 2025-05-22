package CUHA.homepage.service;

import CUHA.homepage.security.dto.UserJoinDto;
import CUHA.homepage.security.dto.UserLoginDto;

public interface UserService {
    public String join(UserJoinDto user);
    public String login(UserLoginDto user);
}
