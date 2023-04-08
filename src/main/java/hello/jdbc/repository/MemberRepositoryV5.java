package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

//jdbctemplate 사용


@Slf4j

public class MemberRepositoryV5 implements  MemberRepository{

    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override//인터페이스 사용시 해주는게 좋음(컴파일러가 안맞으면 오류내줌)
    public Member save(Member member){

        String sql = "insert into member(member_id, money) values(?, ?)";
        //커넥션받고,실행하고,예외변환까지 해줌
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;
    }

    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";
        //단일조회일때queryforobject
        return template.queryForObject(sql, memberRowMapper(), memberId);//인라인 ctrl+art+n 단축키
    }

    public void update(String memberId, int money){

        String sql = "update member set money=? where member_id=?";
        template.update(sql, money, memberId);

    }

    public void delete(String memberId){
        String sql = "delete from member where member_id=?";
        template.update(sql, memberId);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum)->{
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("monney"));
            return member;
        };
    }

    //커넥션 닫는것과 동기화는 스프링에서 다 해줌.




}
