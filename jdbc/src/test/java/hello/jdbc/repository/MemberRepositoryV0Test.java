package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;


import java.sql.SQLException;
import java.util.NoSuchElementException;

@Slf4j
class MemberRepositoryV0Test {//ctrl+shift+t 단축키

    MemberRepositoryV0 repository = new MemberRepositoryV0();

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

    }
}