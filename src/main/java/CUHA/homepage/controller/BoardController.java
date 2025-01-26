package CUHA.homepage.controller;

import CUHA.homepage.security.dto.boardDTO.*;
import CUHA.homepage.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<BoardmessageResponse> postBoard(@RequestBody BoardRequest boardRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.addBoard(request,boardRequest));
    }

//    @GetMapping("/api/board")
//    public ResponseEntity<BoardResponse> getBoard(@RequestParam Long id) {
//        return ResponseEntity.ok().body(boardService.getBoard(id));
//    }

    @GetMapping("/api/board")
    public ResponseEntity<Page<BoardResponse>> getBoards(@RequestParam int page,@RequestParam int size, String username) {
        return ResponseEntity.ok().body(boardService.getBoardsPageByAuthor(page,size,username));
    }

    @GetMapping("/api/boards")
    public ResponseEntity<Page<BoardResponse>> getBoard(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(boardService.getBoardsPage(page,size));
    }

    @PutMapping("/api/board")
    public ResponseEntity<BoardmessageResponse> updateBoard(@RequestBody BoardFindRequest boardFindRequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.updateBoard(boardFindRequest,request));
    }

    @DeleteMapping("/api/board")
    public ResponseEntity<BoardmessageResponse> deleteBoard(@RequestParam Long id, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.deleteBoard(id,request));
    }




}
