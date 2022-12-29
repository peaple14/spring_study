package mixptc.mixptcservice.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import mixptc.mixptcservice.domain.member.Member;
import mixptc.mixptcservice.domain.member.MemberRepository;


@Service
@RequiredArgsConstructor
public class FindService {

    private final MemberRepository memberRepository;

    //이름과 전화번호를 이용해 아이디찾기
   public Member FId(String Name,Long logintel) {
        return memberRepository.findByTel(logintel)
                .filter(m -> m.getName().equals(Name))//이름과 전화번호가 맞을시
                .orElse(null); //다를시 null리턴
    }

    public Member FPW(String Name,String loginid) {
        return memberRepository.findByID(loginid)
                .filter(m -> m.getName().equals(Name))//이름과 전화번호가 맞을시
                .orElse(null); //다를시 null리턴
    }




}





