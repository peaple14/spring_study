package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
//트랜잭션 - 파라미터 연동,풀을 고려한 종료
@RequiredArgsConstructor//final만가지고 생성자 만들어주는것.까먹지말도록.
@Slf4j
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);//트랜잭션 시작할때 오토커밋 끄기
            //비즈니스 로직 실행
            bizLogic(con, fromId, toId, money);
            con.commit();//정상 수행할시에 커밋
        } catch (Exception e) {
            con.rollback();//실패시 롤백
            throw new IllegalStateException(e);
        } finally{
            release(con);//컨트롤,alt,m 단축키
        }

    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);//검증하고 두번째로
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if (con != null) {
            try {
                con.setAutoCommit(true);//다시 오토커밋 키고
                con.close();//종료
            } catch (Exception e) {
                log.info("error message={}","message예시", e);

            }
        }
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {//test에서 안썼음.
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
