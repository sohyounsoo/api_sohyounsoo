package sohyounsoo.callbus.board;

import javax.validation.constraints.NotBlank;

public class WriteRequest {
    @NotBlank(message = "content must be provided")
    private String content;

    protected WriteRequest(){}

    public WriteRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
