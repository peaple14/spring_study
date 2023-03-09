package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//JDBC의 Drivemanager 사용
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member){
        String sql ="insert into member(member_id, money) value (?,?)";

        Connection con = null; //연결용
        PreparedStatement pstmt = null;//데이터베이스에 쿼리날림

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {//sql exception때문에 try catch 써야함.
            throw new RuntimeException(e);
        }

    }

    //alt+ctrl+m 메서드 추출 단축키
    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
