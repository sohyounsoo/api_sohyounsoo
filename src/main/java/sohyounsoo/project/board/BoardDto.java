package sohyounsoo.project.board;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class BoardDto {

    private Long id;
    private String account_id;
    private String content;
    private String account_type;
    private long like_cnt;
    private String like_yn;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private LocalDateTime delete_at;
    private String delete_yn;



    public BoardDto(Board board) {
        copyProperties(board, this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("board_id", id)
            .append("write_id", account_id)
            .append("content", content)
            .append("create_at", create_at)
            .append("update_at", update_at)
            .append("delete_at", delete_at)
            .append("delete_yn", delete_yn)
            .append("account_type", account_type)
            .append("like_cnt", like_cnt)
            .append("like_yn", like_yn)
            .toString();
    }

}
