package sohyounsoo.callbus.board;

import org.springframework.security.core.Authentication;
import sohyounsoo.callbus.errors.NotFoundException;
import sohyounsoo.callbus.security.JwtAuthentication;
import sohyounsoo.callbus.users.User;
import sohyounsoo.callbus.users.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sohyounsoo.callbus.utils.ApiUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sohyounsoo.callbus.utils.ApiUtils.success;
import static sohyounsoo.callbus.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/board")
public class BoardRestController {
    private final BoardService boardService;
    private final UserService userService;

    public BoardRestController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping
    public ApiResult<List<BoardDto>> findAll(@AuthenticationPrincipal JwtAuthentication authentication) throws NullPointerException {

        List<BoardDto> boardList = new ArrayList<>();

        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could not found users for " + authentication.id));

        boardList = boardService.findAll(user.getId())
                .stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());

        System.out.println(authentication);

        return success(boardList);
    }

    @PostMapping("/write")
    public ApiUtils.ApiResult<?> write(@Valid @RequestBody WriteRequest request,
                                       @AuthenticationPrincipal JwtAuthentication authentication) throws Exception {
        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could not found users for " + authentication.id));

        String content = request.getContent();
        Board board = new Board(user.getId(), content, LocalDateTime.now());
        Long BoardId = boardService.createBoard(board);

        return success(BoardId);
    }

    @PatchMapping("/{boardId}/update")
    public ApiUtils.ApiResult<Boolean> update(@PathVariable("boardId") Long board_id,
                                              @AuthenticationPrincipal JwtAuthentication authentication, @Valid @RequestBody WriteRequest request) throws Exception {
        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could not found users for " + authentication.id));
        long userId = user.getId();

        BoardDto findBoard = boardService.findById(board_id, user.getId())
                .map(BoardDto::new)
                .orElseThrow(() -> new NotFoundException("Could not update for board_id " + board_id + " because not user"));

        String content = request.getContent();
        Board board = new Board(LocalDateTime.now(), board_id, content);
        boardService.updateById(board);

        return success(true);
    }

    @DeleteMapping("/{boardId}/delete")
    public ApiUtils.ApiResult<Boolean> delete(@PathVariable("boardId") Long board_id,
                                              @AuthenticationPrincipal JwtAuthentication authentication) throws Exception {
        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could not found users for " + authentication.id));

        BoardDto findBoard = boardService.findById(board_id, user.getId())
                .map(BoardDto::new)
                .orElseThrow(() -> new NotFoundException("Could not delete for board_id " + board_id + " because not user"));

        Board board = new Board(LocalDateTime.now(), board_id);

        boardService.deleteById(board);

        return success(true);
    }

    @PostMapping("/{boardId}/like")
    public ApiUtils.ApiResult<Boolean> isLike(@PathVariable("boardId") Long board_id,
                                              @AuthenticationPrincipal JwtAuthentication authentication) throws Exception {
        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could not found users for " + authentication.id));

        Long userId = user.getId();

        int dupCnt = boardService.isLikeChk(board_id, user.getId()); // 중복체크
        if(dupCnt > 0) {
            throw new IllegalStateException("Could not like for board_id " + board_id + " because you have already Like");
        }

        int isBoardChk = boardService.isBoardChk(board_id);
        if(isBoardChk == 0) {
            throw new IllegalStateException("Could not found for board_id " + board_id );
        }

        boardService.updateLike(board_id, user.getId(), LocalDateTime.now());

        return success(true);
    }

    @GetMapping("/{boardId}/history")
    public ApiResult<List<LikeDto>> likeHistory(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable("boardId") Long boardId) throws Exception {


        List<LikeDto>  hisoryList = boardService.findByLike(boardId)
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toList());

        return success(hisoryList);
    }

    @ExceptionHandler(NullPointerException.class)
    public ApiResult<List<BoardDto>> nullex(Exception e) {
//        System.err.println(e.getClass());

        List<BoardDto> boardList = boardService.findAllNotUser()
                .stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());

        return success(boardList);
    }

}
