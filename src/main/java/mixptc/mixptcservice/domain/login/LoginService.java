package mixptc.mixptcservice.domain.login;

import lombok.RequiredArgsConstructor;

import mixptc.mixptcservice.domain.member.Member;
import mixptc.mixptcservice.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password)) //아이디와 비밀번호가 맞을시
                .orElse(null); //다를시 null리턴
    }


}
