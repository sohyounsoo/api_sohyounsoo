package sohyounsoo.project.board;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll(long id) {
        return boardRepository.findAll(id);
    }

    public Long createBoard(Board board) {
        return boardRepository.createBoard(board);
    }

    public void updateById(Board board) {
        boardRepository.updateById(board);
    }

    public Optional<Board> findById(Long boardId, long userId) {
        return boardRepository.findById(boardId, userId);
    }

    public void deleteById(Board board) {
        boardRepository.deleteById(board);
    }

    public int isLikeChk(Long boardId, long userId) {
        return boardRepository.isLikeChk(boardId, userId);
    }

    public void updateLike(Long boardId, Long userId, LocalDateTime now) {
        boardRepository.updateLike(boardId, userId, now);
    }

    public int isBoardChk(Long boardId) {
        return boardRepository.isBoardChk(boardId);
    }

    public List<Like> findByLike(long boardId) {
        return boardRepository.findByLike(boardId);
    }

    public List<Board> findAllNotUser() {
        return boardRepository.findAllNotUser();
    }
}
