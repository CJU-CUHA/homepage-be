package CUHA.homepage.controller;

import CUHA.homepage.model.Comment;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import CUHA.homepage.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public CommentMessageResponse addComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return commentService.addComment(commentRequest,request);
    }

    @GetMapping("/api/comments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/api/comment")
    public List<CommentResponse> getCommentByBoard(@RequestParam Long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("/api/comment")
    public CommentMessageResponse updateComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }

    @DeleteMapping("/api/comment")
    public CommentMessageResponse deleteComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }


}
