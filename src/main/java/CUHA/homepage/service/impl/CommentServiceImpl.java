package CUHA.homepage.service.impl;

import CUHA.homepage.model.Comment;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.CommentRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.commentDTO.CocommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentMessageResponse;
import CUHA.homepage.security.dto.commentDTO.CommentRequest;
import CUHA.homepage.security.dto.commentDTO.CommentResponse;
import CUHA.homepage.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Override
    public CommentMessageResponse addComment(CommentRequest commentRequest, HttpServletRequest request) {
        Comment comment = Comment.builder()
                .comment(commentRequest.getComment())
                .board(boardRepository.findById(commentRequest.getBoard_id()).get())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .created_at(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        return CommentMessageResponse.builder().message("댓글이 등록되었습니다.").build();
    }

    @Override
    public CommentMessageResponse addCocomment(CocommentRequest cocommentRequest, HttpServletRequest request) {
        if (commentRepository.findById(cocommentRequest.getComment_id()).get().getParent() != null) {
            return CommentMessageResponse.builder().message("대댓글에는 댓글을 달 수 없습니다.").build();
        }

        Long boardId = commentRepository.findById(cocommentRequest.getComment_id()).get().getBoard().getId();
        Comment cocoment = Comment.builder()
                .parent(commentRepository.findById(cocommentRequest.getComment_id()).get()) // 부모 지정
                .comment(cocommentRequest.getComment())
                .board(boardRepository.findById(boardId).get())
                .author(userRepository.findByUsername(request.getSession().getAttribute("user").toString()).get())
                .created_at(LocalDateTime.now())
                .build();
        commentRepository.save(cocoment); // 대댓글 저장

        // 부모 댓글에 자식 댓글 지정
        Comment parent = commentRepository.findById(cocommentRequest.getComment_id()).get();
        parent.setChildren(cocoment);
        commentRepository.save(parent);

        return CommentMessageResponse.builder().message("대댓글이 등록되었습니다.").build();
    }

    @Override
    public List<CommentResponse> getAllComments() {

        return commentRepository.findAll().stream().map(x->CommentResponse.builder()
                .id(x.getId())
                .comment(x.getComment())
                .author(x.getAuthor().getUsername()).build()).toList();
    }

    @Override
    public List<CommentResponse> getCommentById(Long id) {
        return commentRepository.findAllByBoard_Id(id).stream().map(x->CommentResponse.builder()
                .id(x.getId()).comment(x.getComment()).author(x.getAuthor().getUsername()).build()).toList();
    }

    @Override
    public CommentMessageResponse deleteComment(Long id,HttpServletRequest request) {
        Optional<Comment> comment =commentRepository.findById(id);
        if(!comment.isPresent()) {
            throw new NotFoundException("해당하는 게시물이 없습니다.");
        }

        if(request.getSession().getAttribute("user").toString().equals(comment.get().getAuthor().getUsername())){
            commentRepository.deleteById(id);
        }
        else{
            return CommentMessageResponse.builder().message("본인의 댓글만 삭제할 수 있습니다..").build();
        }

        return CommentMessageResponse.builder().message("댓글이 삭제되었습니다.").build();
    }

    @Override
    public CommentMessageResponse updateComment(Long id,CommentRequest commentRequest,HttpServletRequest request) {
        Optional<Comment> comment=commentRepository.findById(id);
        if(!comment.isPresent()) {
            return CommentMessageResponse.builder().message("존재하지 않는 게시물입니다.").build();
        }
        Comment updateComment=comment.get();
        if(!request.getSession().getAttribute("user").equals(updateComment.getAuthor().getUsername())) {
            return CommentMessageResponse.builder().message("본인의 게시물만 수정이 가능합니다.").build();
        }

        updateComment.setComment(commentRequest.getComment());
        commentRepository.save(updateComment);
        return CommentMessageResponse.builder().message("수정되었습니다.").build();

    }

    @Override
    public Page<CommentResponse> getCommentsByBoard_Id(Long boardId,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        Long id=boardRepository.findById(boardId).get().getId();
        return commentRepository.findAllByBoard_Id(id,pageable).map(x->CommentResponse.builder()
                .id(x.getId())
                .comment(x.getComment())
                .author(x.getAuthor().getUsername())
                .build());
    }
}
