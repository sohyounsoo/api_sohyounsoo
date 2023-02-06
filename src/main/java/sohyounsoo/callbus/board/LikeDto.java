package sohyounsoo.callbus.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class LikeDto {
    private long board_id;
    private String account_id;
    private LocalDateTime create_at;

    public LikeDto(Like like) {
        copyProperties(like, this);
    }
}
