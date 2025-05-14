package CUHA.homepage.security.dto.commentDTO;

import CUHA.homepage.model.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChildCommentResponse {
    private Long commentId;
    private Long parentId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
//    private Long boardId;

    public static ChildCommentResponse toDTO(Comment child) {
        return ChildCommentResponse.builder()
                .commentId(child.getId())
                .comment(child.getComment())
                .createdAt(child.getCreated_at())
                .updatedAt(child.getUpdated_at())
                .userId(child.getAuthor().getId())
                .parentId(child.getParent().getId())
                .build();
    }
}
