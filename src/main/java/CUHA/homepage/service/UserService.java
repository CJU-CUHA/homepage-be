package CUHA.homepage.service;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.security.dto.userDTO.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserJoinResponse addUser(UserjoinRequest user);
    UserRUDResponse updateUser(UserjoinRequest user);
    UserRUDResponse deleteUser(UserRUDRequest user);
    UserFindResponse getUser(String username);
//    UserLoginResponse loginUser(UserLoginRequest user);
    UserLoginResponse loginUser(UserLoginRequest user, HttpServletRequest request);
    List<UserFindResponse> getUsers();
    Page<UserFindResponse> getUsers(int page, int size);
//    UserRUDResponse deactivateUser(HttpServletRequest req);
//    UserRUDResponse activateUser(HttpServletRequest req);
    UserRUDResponse updateUserAdmin(HttpServletRequest req,UserUpdateRequest user);
    Long getScore(String user);
    UserRUDResponse setScore(UserRUDRequest user, Long score);
    UserRUDResponse setUserRole(UserRUDRequest user, UserRole userRole,HttpServletRequest req);
    UserRole getUserRole(UserRUDRequest user);
    Gender getGender(String user);
}
