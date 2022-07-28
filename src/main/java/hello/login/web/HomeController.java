package hello.login.web;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    // @GetMapping("/")
    public String home() {
        return "home";
    }


    /*@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {//쿠키도 가져옴
        if (memberId == null) {
            return "home";
        }
        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {//쿠키를 너무 옛날에 만들었을시
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }*/

    /*@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {//쿠키도 가져옴

        //세션 관리자에 저장된 회원정보조회회
        Member member = (Member)sessionManager.getSession(request);

        //로그인
        if (member == null) {//쿠키를 너무 옛날에 만들었을시
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }*/

    /*@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);//처음들어온사람은 세션이없고 세션만들필요없음.
        if (session != null) {
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);


        //세션에 회원데이터가없을시
        if (loginMember == null) {//쿠키를 너무 옛날에 만들었을시
            return "home";
        }
        //세션이 유지되면 로그인으로이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }*/

    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                                Member loginMember, Model model) {


        //세션에 회원데이터가없을시
        if (loginMember == null) {//쿠키를 너무 옛날에 만들었을시
            return "home";
        }
        //세션이 유지되면 로그인으로이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}