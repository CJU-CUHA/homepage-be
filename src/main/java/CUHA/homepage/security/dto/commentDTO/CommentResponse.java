package CUHA.homepage.security.dto.commentDTO;

import CUHA.homepage.model.Comment;
import CUHA.homepage.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String author;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String comment;

    private List<ChildCommentResponse> children;

    public static CommentResponse toDTO(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .created_at(comment.getCreated_at())
                .updated_at(comment.getUpdated_at())
                .author(comment.getAuthor().getUsername())
//                .boardId(comment.getBoard().getId())
                .build();
    }

    // 자식이 있는 댓글의 toDTO
    public static CommentResponse toDTOhaveChild(Comment comment, List<ChildCommentResponse> child) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .children(child)
                .created_at(comment.getCreated_at())
                .updated_at(comment.getUpdated_at())
                .author(comment.getAuthor().getUsername())
//                .boardId(comment.getBoard().getId())
                .build();
    }
}
