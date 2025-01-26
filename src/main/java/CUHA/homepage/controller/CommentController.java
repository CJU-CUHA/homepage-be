package CUHA.homepage.controller;

import CUHA.homepage.model.Comment;
import CUHA.homepage.security.dto.commentDTO.CocommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import CUHA.homepage.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<CommentMessageResponse> addComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(commentService.addComment(commentRequest,request));
    }

    @PostMapping("/api/cocomment")
    public ResponseEntity<CommentMessageResponse> addCocomment(@RequestBody CocommentRequest cocommentRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(commentService.addCocomment(cocommentRequest,request));
    }

    @GetMapping("/api/comments")
    public ResponseEntity<Page<CommentResponse>> getCommentByBoard(@RequestParam Long id, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(commentService.getCommentsByBoard_Id(id,page,size));
    }

    @PutMapping("/api/comment")
    public ResponseEntity<CommentMessageResponse> updateComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }

    @DeleteMapping("/api/comment")
    public ResponseEntity<CommentMessageResponse> deleteComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return null;
    }


}
