package mixptc.mixptcservice.domain.member;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindService {

    private final MemberRepository memberRepository;

    //전화번호를 이용해 아이디찾기
   /* public Member FId(Long tel) {
        return memberRepository.findByTel(findTel)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null); //다를시 null리턴
    }

    //아이디와 전화번호를 이용해 비밀번호찾기
    public Member FPwd(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password)) //아이디와 비밀번호가 맞을시
                .orElse(null); //다를시 null리턴
    }*/



    /*public Member login(String loginId, String password) { //로그인서비스
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password)) //아이디와 비밀번호가 맞을시
                .orElse(null); //다를시 null리턴
    }*/

    /* public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }*/
}