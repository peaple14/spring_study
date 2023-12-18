package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

//sqlexceptiontranslator 추가


@Slf4j

public class MemberRepositoryV4_2 implements  MemberRepository{

    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;




    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override//인터페이스 사용시 해주는게 좋음(컴파일러가 안맞으면 오류내줌)
    public Member save(Member member){

        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection con = null; //연결용
        PreparedStatement pstmt = null;//데이터베이스에 쿼리날림

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());//sql의 ?칸의 첫번째에 들어감.
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();//실제 데이터베이스에 전달
            return member;
        } catch (SQLException e) {//sql exception때문에 try catch 써야함.
            throw exTranslator.translate("save", sql, e);//어떤작업에서 일어났다,sql,오류코드
        }finally {
            close(con, pstmt, null); //정리
        }
    }

    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con= getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else{//문제터진곳 확인
                throw new NoSuchElementException("member not fount memberId=" + memberId);
            }

        } catch (SQLException e) {
            throw exTranslator.translate("findById", sql, e);//어떤작업에서 일어났다,sql,오류코드
        }finally{
            close(con, pstmt, rs);
        }
    }



    public void update(String memberId, int money){

        String sql = "update member set money=? where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw exTranslator.translate("update", sql, e);//어떤작업에서 일어났다,sql,오류코드
        } finally {
            close(con, pstmt, null);
        }
    }


    public void delete(String memberId){
        String sql = "delete from member where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw exTranslator.translate("delete", sql, e);//어떤작업에서 일어났다,sql,오류코드
        } finally {
            close(con, pstmt, null);
        }
    }




    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        //트랜잭션 동기화사용시 DataSourceUtils를 사용해야함.(커넥션 닫을때)
        DataSourceUtils.releaseConnection(con,dataSource);
    }

    //alt+ctrl+m 메서드 추출 단축키
    private Connection getConnection() throws SQLException {
        //트랜잭션 동기화사용시 DataSourceUtils를 사용해야함.(커넥션얻을때)
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }
}
