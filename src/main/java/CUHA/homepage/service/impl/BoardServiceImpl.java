package CUHA.homepage.service.impl;

import CUHA.homepage.domain.Board;
import CUHA.homepage.domain.BoardReaction;
import CUHA.homepage.domain.ReactionType;
import CUHA.homepage.domain.User;
import CUHA.homepage.exception.UserNotFoundException;
import CUHA.homepage.repository.BoardReactionRepository;
import CUHA.homepage.repository.BoardRepository;
import CUHA.homepage.repository.UserRepository;
import CUHA.homepage.security.dto.BoardReactionRequestDto;
import CUHA.homepage.security.dto.BoardReactionResponseDto;
import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import CUHA.homepage.exception.BoardNotFoundException;
import CUHA.homepage.security.jwt.JWTUtil;
import CUHA.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardReactionRepository boardReactionRepository;
    private final JWTUtil jwtUtil;


    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        User user = userRepository.findById(boardRequestDto.getAuthor()).orElseThrow(() -> new UserNotFoundException("id: " + boardRequestDto.getAuthor()));

        Board saveBoard = Board.builder()
                .title(boardRequestDto.getTitle())
                .author(user)
                .content(boardRequestDto.getContent())
                .like(0L)
                .dislike(0L)
                .build();

        boardRepository.save(saveBoard);

        return BoardResponseDto.from(saveBoard);
    }

    @Override
    public BoardResponseDto getBoard(Long id, String token) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));

        String username = null;
        if (token != null) {
            try {
                username = jwtUtil.getUsername(token);
            } catch (Exception e) {
                // 토큰이 잘못된 경우에도 그냥 넘어감
            }
        }

        String userReaction = null;
        if (username != null) {
            userReaction = userRepository.findByUsername(username)
                    .flatMap(user -> boardReactionRepository.findByUserAndBoard(user, board))
                    .map(reaction -> reaction.getReaction().toString())
                    .orElse(null);
        }
        return BoardResponseDto.from(board, userReaction);
    }

    @Override
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, String token) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));

        String username = jwtUtil.getUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        if(!board.getAuthor().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        boardRepository.save(board);

        return BoardResponseDto.from(board);
    }

    @Override
    public void deleteBoard(Long id, String token) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));

        String username = jwtUtil.getUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        if(!board.getAuthor().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        // Board File 삭제 로직

        boardRepository.deleteById(id);
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll();

        return boardList.stream().map(BoardResponseDto::from).toList();
    }

    @Override
    public Page<BoardResponseDto> getBoards(int page, String token) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findAll(pageable);

        return mapWithUserReaction(boards, token);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByAuthor(Long author, int page, String token) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByAuthor(author, pageable);

        return mapWithUserReaction(boards, token);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByTitle(String keyword, int page, String token) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByTitleContaining(keyword, pageable);

        return mapWithUserReaction(boards, token);
    }

    @Override
    public Page<BoardResponseDto> getBoardsByContent(String keyword, int page, String token) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        Page<Board> boards = boardRepository.findByContentContaining(keyword, pageable);

        return mapWithUserReaction(boards, token);
    }

    @Override
    @Transactional
    public BoardReactionResponseDto reactToBoard(BoardReactionRequestDto boardReactionRequestDto, String token) {
        Board board = boardRepository.findById(boardReactionRequestDto.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException(boardReactionRequestDto.getBoardId()));

//        if (token == null) {
//            throw new TokenNotFoundException();
//        }

        String username = jwtUtil.getUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        ReactionType newReaction = ReactionType.valueOf(boardReactionRequestDto.getUserReaction());
        Optional<BoardReaction> existingReactionOpt = boardReactionRepository.findByUserAndBoard(user, board);
        String resultReaction = newReaction.toString();

        if (existingReactionOpt.isPresent()) {
            BoardReaction existingReaction = existingReactionOpt.get();
            ReactionType oldReaction = existingReaction.getReaction();

            if (oldReaction == newReaction) {
                // 반응 취소
                boardReactionRepository.delete(existingReaction);
                if (newReaction == ReactionType.LIKE) {
                    board.setLike(board.getLike() - 1);
                } else {
                    board.setDislike(board.getDislike() - 1);
                }
                resultReaction = null;
            } else {
                // 반응 변경
                if (oldReaction == ReactionType.LIKE) {
                    board.setLike(board.getLike() - 1);
                    board.setDislike(board.getDislike() + 1);
                } else {
                    board.setLike(board.getLike() + 1);
                    board.setDislike(board.getDislike() - 1);
                }
                existingReaction.setReaction(newReaction);
                boardReactionRepository.save(existingReaction);
            }
        } else {
            // 새로운 반응
            BoardReaction newReactionEntity = BoardReaction.builder()
                    .user(user)
                    .board(board)
                    .reaction(newReaction)
                    .build();
            boardReactionRepository.save(newReactionEntity);

            if (newReaction == ReactionType.LIKE) {
                board.setLike(board.getLike() + 1);
            } else {
                board.setDislike(board.getDislike() + 1);
            }
        }

        boardRepository.save(board);

        return BoardReactionResponseDto.builder()
                .boardId(board.getId())
                .likeCount(board.getLike())
                .dislikeCount(board.getDislike())
                .userReaction(resultReaction)
                .build();
    }


    private Page<BoardResponseDto> mapWithUserReaction(Page<Board> boards, String token) {
        User user = extractUserFromToken(token);
        return boards.map(board -> {
            String reaction = null;
            if (user != null) {
                reaction = boardReactionRepository.findByUserAndBoard(user, board)
                        .map(r -> r.getReaction().toString())
                        .orElse(null);
            }
            return BoardResponseDto.from(board, reaction);
        });
    }

    private User extractUserFromToken(String token) {
        try {
            String username = jwtUtil.getUsername(token);
            return userRepository.findByUsername(username).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
