package sohyounsoo.callbus.users;


import java.time.LocalDateTime;

public class EmptyUser  extends User {
    public EmptyUser(String nickname, String account_id, String password, Account_type accountType) {
        super(null, null, null, accountType);
    }
}
