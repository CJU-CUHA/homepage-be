package CUHA.homepage.service;

import CUHA.homepage.security.dto.commentDTO.AllCommentsResponse;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CommentService {
    CommentMessageResponse addComment(CommentRequest commentRequest, HttpServletRequest resquest);
    List<CommentResponse> getAllComments();
    List<CommentResponse> getCommentById(Long id);
    CommentMessageResponse deleteComment(Long id,HttpServletRequest resquest);
    CommentMessageResponse updateComment(Long id,CommentRequest commentRequest,HttpServletRequest resquest);

}
