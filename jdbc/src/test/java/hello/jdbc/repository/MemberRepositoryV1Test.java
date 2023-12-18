package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {//ctrl+shift+t 단축키

    MemberRepositoryV2 repository;

    @BeforeEach
//각테스트가 실행되기직전에 한번 실행
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
//        repository = new MemberRepositoryV1(dataSource);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepositoryV2(dataSource);


    }

    @Test
    void crud() throws SQLException {
        //save한것
        Member member = new Member("memberV21", 10000);
        repository.save(member);

        //save잘됬는지 확인
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);

        //돈 10000원에서30003으로
        repository.update(member.getMemberId(), 30003);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(30003);

        //삭제
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

        try {
            Thread.sleep(1000);//너무빨라서 잘 안보임.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}