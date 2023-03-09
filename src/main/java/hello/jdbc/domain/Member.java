package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {

    private String memberId;
    private int money;

    //기본생성자
    public Member(){
    }

    //alt+insert 생성자단축
    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
