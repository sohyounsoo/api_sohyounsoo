package sohyounsoo.callbus.users;



public enum Account_type {

    Realtor("공인중개사"),
    Lessor("임대인"),
    Lessee("임차인");

    private final String name;

    Account_type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


