package CUHA.homepage.controller;

import CUHA.homepage.exception.TokenNotFoundException;
import CUHA.homepage.exception.UserNotFoundException;
import CUHA.homepage.security.dto.BoardReactionRequestDto;
import CUHA.homepage.security.dto.BoardReactionResponseDto;
import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import CUHA.homepage.service.BoardService;
import CUHA.homepage.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(
            @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.createBoard(boardRequestDto));
    }

//    @GetMapping
//    public ResponseEntity<List<BoardResponseDto>> getAllBoard() {
//        return ResponseEntity.ok(boardService.getBoards());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {

        return ResponseEntity.ok(boardService.getBoard(id, resolveToken(token)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto boardRequestDto,
            @RequestHeader(value = "Authorization") String token) {

        return ResponseEntity.ok(boardService.updateBoard(id, boardRequestDto, resolveToken(token)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization") String token) {

        boardService.deleteBoard(id, resolveToken(token));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<BoardResponseDto>> getAllBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {

        return ResponseEntity.ok(boardService.getBoards(page, resolveToken(token)));
    }
    @GetMapping("/author/{authorId}")
    public ResponseEntity<Page<BoardResponseDto>> getBoardsByAuthor(
            @PathVariable Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {

        return ResponseEntity.ok(boardService.getBoardsByAuthor(authorId, page, resolveToken(token)));
    }

    // 제목 검색
    @GetMapping("/search/title")
    public ResponseEntity<Page<BoardResponseDto>> searchByTitle(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {

        return ResponseEntity.ok(boardService.getBoardsByTitle(keyword, page, resolveToken(token)));
    }

    // 내용 검색
    @GetMapping("/search/content")
    public ResponseEntity<Page<BoardResponseDto>> searchByContent(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {

        return ResponseEntity.ok(boardService.getBoardsByContent(keyword, page, resolveToken(token)));
    }

    @PostMapping("/reaction")
    public ResponseEntity<BoardReactionResponseDto> reactToBoard(
            @RequestBody BoardReactionRequestDto boardReactionRequestDto,
            @RequestHeader(value = "Authorization") String token
    ) {

        BoardReactionResponseDto response = boardService.reactToBoard(boardReactionRequestDto, resolveToken(token));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> handleBoardNotFound(BoardNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<String> handleTokenNotFoundException(TokenNotFoundException e) {
        return ResponseEntity.status(HttpStatus. UNAUTHORIZED).body(e.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    private String resolveToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}

