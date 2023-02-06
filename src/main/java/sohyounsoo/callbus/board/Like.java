package sohyounsoo.callbus.board;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;


@Getter
@Setter
public class Like {

    private long board_id;
    private String account_id;
    private LocalDateTime create_at;

    public Like(long boardId, String accountId, LocalDateTime createAt) {
        this.board_id = boardId;
        this.account_id = accountId;
        this.create_at =createAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("board_id", board_id)
                .append("account_id", account_id)
                .append("create_at", create_at)
                .toString();
    }

    public static class Builder {
        private long board_id;
        private String account_id;
        private LocalDateTime create_at;
        public Builder() {/*empty*/}

        public Builder(Like like) {
            this.board_id = like.board_id;
            this.account_id = like.account_id;
            this.create_at = like.create_at;
        }

        public Builder board_id(long board_id) {
            this.board_id = board_id;
            return this;
        }

        public Builder account_id(String account_id) {
            this.account_id = account_id;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public Like build() {
            return new Like(
                    board_id,
                    account_id,
                    create_at
            );
        }
    }


}
