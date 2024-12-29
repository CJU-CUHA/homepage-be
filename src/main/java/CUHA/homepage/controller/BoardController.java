package CUHA.homepage.controller;

import CUHA.homepage.security.dto.boardDTO.*;
import CUHA.homepage.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/api/board")
    public ResponseEntity<BoardResponse> getBoard(@RequestParam Long id) {
        return ResponseEntity.ok().body(boardService.getBoard(id));
    }

    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> getBoard() {
        return ResponseEntity.ok().body(boardService.getBoards());
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
