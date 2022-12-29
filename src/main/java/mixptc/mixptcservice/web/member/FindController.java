package mixptc.mixptcservice.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mixptc.mixptcservice.domain.member.FindService;
import mixptc.mixptcservice.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class FindController {

/*221229할일
* 1.비번칠때 오류뜨는거
* 2.먼가 망한느낌이 든다.
* 3.뭔가 만지니 아이디도 안됨.
* */

    private final FindService findService;


    @GetMapping("/findId")
    public String findI(@ModelAttribute("findidform") FindIdForm form) {
        return "members/findId";
    }

    @PostMapping("/findId")
    public String FindI(@Validated @ModelAttribute("findidform") FindIdForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/findId";
        }

        Member findMember = findService.FId(form.getFindname(), form.getFindtel()); //여기서 이름,전화번호가 같은걸로 찾음.
        if (findMember == null) {
            bindingResult.reject("findFail", "아이디가 존재하지 않습니다.");
            return "/members/findId";
        }
        model.addAttribute("findMember", findMember);
        return "members/findIdResult";

    }

    @GetMapping("/findPassword")
    public String findP(@ModelAttribute("FindpasswordForm") FindpasswordForm form) {
        return "members/findPassword";
    }


    @PostMapping("/findPassword")
    public String findP(@Validated @ModelAttribute("FindpasswordForm") FindpasswordForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/findPassword";
        }

        Member findMember = findService.FPW(form.getFindname(), form.getFindId());
        if (findMember == null) {
            bindingResult.reject("findFail", "비밀번호가 존재하지 않습니다.");
            return "/members/findPassword";
        }
        model.addAttribute("findMember", findMember);
        return "members/findPasswordResult";

    }
}
