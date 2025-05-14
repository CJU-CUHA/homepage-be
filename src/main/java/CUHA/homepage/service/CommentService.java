package CUHA.homepage.service;

import CUHA.homepage.security.dto.commentDTO.CocommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    CommentMessageResponse addComment(CommentRequest commentRequest, HttpServletRequest request);
    CommentMessageResponse addCocomment(CocommentRequest cocommentRequest, HttpServletRequest request);
    List<CommentResponse> getAllComments();
    List<CommentResponse> getCommentById(Long id);
    CommentMessageResponse deleteComment(Long id,HttpServletRequest request);
    CommentMessageResponse updateComment(Long id,CommentRequest commentRequest,HttpServletRequest request);

    Page<CommentResponse> getCommentsByBoard_Id(Long boardId,int page,int size);

}
