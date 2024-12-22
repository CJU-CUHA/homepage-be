package CUHA.homepage.service;

import CUHA.homepage.model.Gender;
import CUHA.homepage.model.UserRole;
import CUHA.homepage.security.dto.userDTO.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    UserJoinResponse addUser(UserjoinRequest user);
    UserRUDResponse updateUser(UserjoinRequest user);
    UserRUDResponse deleteUser(UserRUDRequest user);
    UserFindResponse getUser(String username);
    UserLoginResponse loginUser(UserLoginRequest user);
    List<UserFindResponse> getUsers();
//    UserRUDResponse deactivateUser(HttpServletRequest req);
//    UserRUDResponse activateUser(HttpServletRequest req);
    UserRUDResponse updateUserAdmin(HttpServletRequest req,UserUpdateRequest user);
    Long getScore(String user);
    UserRUDResponse setScore(UserRUDRequest user, Long score);
    UserRUDResponse setUserRole(UserRUDRequest user, UserRole userRole,HttpServletRequest req);
    UserRole getUserRole(UserRUDRequest usert);
    Gender getGender(String user);
}
