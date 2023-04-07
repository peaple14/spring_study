package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


//예외 누수 해결
//sqlexception 제거
//memberrepository 인터페이스 의존
@Slf4j
public class MemberServiceV4 {
    private final MemberRepository memberRepository;

    public MemberServiceV4(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //이 메서드 사용시 트랜잭션 건다는 에노테이션
    @Transactional
    public void accountTransfer(String fromId, String toId, int money){
                bizLogic(fromId, toId, money);
    }

    private void bizLogic(String fromId, String toId, int money){
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);//검증하고 두번째로
        memberRepository.update(toId, toMember.getMoney() + money);
    }


    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {//test에서 안썼음.
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
