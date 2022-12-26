package mixptc.mixptcservice.web.member;

import lombok.RequiredArgsConstructor;
import mixptc.mixptcservice.domain.login.LoginService;
import mixptc.mixptcservice.domain.member.FindService;
import mixptc.mixptcservice.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class FindController {

    private final FindService findService;


    @GetMapping("/findId")
    public String findI(@ModelAttribute("findidform") FindIdForm form) {
        return "members/findId";
    }

    @PostMapping("/findId")
    public String FindI(@Valid @ModelAttribute FindIdForm form , BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "members/findId";
        }

        Member findMember = findService.FId(form.getFindname(),form.getFindtel());


        if (findMember == null) {
            bindingResult.reject("findFail", "아이디가 존재하지 않습니다.");
            return "login/loginForm";
        }

        return "members/findIdResult";

    }

  /*  @GetMapping("/findIdResult")
    public String FindIR(@ModelAttribute Member member, Model model) {


    }*/


    @GetMapping("/findPassword")
    public String findP(@ModelAttribute("member") FindpasswordForm form) {
        return "members/findPassword";
    }
}
