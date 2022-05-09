package hello.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();
    //테스트시 테스트끝나고 afterEach 무조건 실행됨.
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        //멤버를 찾은후
        Member findMember = memberRepository.findById(savedMember.getId());
        //찾은멤버는 같아야한다 저장된 멤버와
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member1", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1,member2);

    }
}
