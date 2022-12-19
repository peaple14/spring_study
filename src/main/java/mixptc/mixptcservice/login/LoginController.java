package mixptc.mixptcservice.login;

import lombok.RequiredArgsConstructor;
import mixptc.mixptcservice.domain.login.LoginService;
import mixptc.mixptcservice.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController { //로그인 담당하는 컨트롤러
    //컨트롤러는 종류별로 나누기
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {//에러 있을시
            return "login/loginForm";
        }

        //로그인할때 받은거 넣고
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        //만약 틀릴시
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지않음.");
            return "login/loginForm";
        }

        //성공시
        return "redirect:/items";

    }
}
