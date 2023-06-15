package sohyounsoo.project.board;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;


@Getter
@Setter
public class Board {

    private Long id;
    private Long users_id;
    private String account_id;
    private String content;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private LocalDateTime delete_at;
    private String delete_yn;
    private String account_type;
    private long like_cnt;
    private String like_yn;

    public Board(Long id, String account_id, String content, LocalDateTime create_at, LocalDateTime update_at, LocalDateTime delete_at, String delete_yn, String account_type,
                 long like_cnt, String like_yn) {
        this.id = id;
        this.account_id = account_id;
        this.content = content;
        this.create_at = create_at;
        this.update_at = update_at;
        this.delete_at = delete_at;
        this.delete_yn = delete_yn;
        this.account_type = account_type;
        this.like_cnt = like_cnt;
        this.like_yn = like_yn;
    }

    public Board(Long users_id, String content, LocalDateTime create_at) {
        this.users_id = users_id;
        this.content = content;
        this.create_at = create_at;
    }

    public Board(LocalDateTime update_at, Long id, String content) {
        this.update_at = update_at;
        this.id = id;
        this.content = content;
    }

    public Board(LocalDateTime delete_at, Long id) {
        this.delete_at = delete_at;
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
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

    static public class Builder {
        private Long id;
        private String account_id;
        private String content;
        private LocalDateTime create_at;
        private LocalDateTime update_at;
        private LocalDateTime delete_at;
        private String delete_yn;
        private String account_type;
        private Long like_cnt;
        private String like_yn;
        public Builder() {/*empty*/}

        public Builder(Board board) {
            this.id = board.id;
            this.account_id = board.account_id;
            this.content = board.content;
            this.create_at = board.create_at;
            this.update_at = board.update_at;
            this.delete_at = board.delete_at;
            this.account_type = board.account_type;
            this.like_cnt = board.like_cnt;
            this.like_yn = board.like_yn;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public Builder update_at(LocalDateTime update_at) {
            this.update_at = update_at;
            return this;
        }

        public Builder delete_at(LocalDateTime delete_at) {
            this.delete_at = delete_at;
            return this;
        }

        public Builder delete_yn(String delete_yn) {
            this.delete_yn = delete_yn;
            return this;
        }

        public Builder account_type(String account_type) {
            this.account_type = account_type;
            return this;
        }

        public Builder like_cnt(Long like_cnt) {
            this.like_cnt = like_cnt;
            return this;
        }

        public Builder like_yn(String like_yn) {
            this.like_yn = like_yn;
            return this;
        }

        public Builder accountId(String account_id) {
            this.account_id = account_id;
            return this;
        }

        public Board build() {
            return new Board(
                id,
                account_id,
                content,
                create_at,
                update_at,
                delete_at,
                delete_yn,
                account_type,
                like_cnt,
                like_yn
            );
        }
    }


}

