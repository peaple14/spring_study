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

/*    221227 - 할것
1.bindingresult수정
2.맞을시 findid로 이동수정
3.findidresult->webconfig 수정
4.password만들기*/

    private final FindService findService;


    @GetMapping("/findId")
    public String findI(@ModelAttribute("findidform") FindIdForm form) {
        log.info("result3={}", form);
        return "members/findId";
    }

    @PostMapping("/findId")
    public String FindI(@Validated @ModelAttribute FindIdForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "members/findId";
        }

        Member findMember = findService.FId(form.getFindname(), form.getFindtel()); //여기서 이름,전화번호가 같은걸로 찾음.
        log.info("result1={}", findMember);
        if (findMember == null) {
            bindingResult.reject("findFail", "아이디가 존재하지 않습니다.");
            return "/members/findId";
        }
        model.addAttribute(findMember);

        return "redirect:/findIdResult";

    }

    @GetMapping("/findIdResult")
    public String findIR(@ModelAttribute FindIdForm form, Model model) {
        log.info("result2={}", form);
        model.addAttribute("fid", form);
        return "redirect:/findIdResult";
    }



  /*  @GetMapping("/findIdResult")
    public String FindIR(@ModelAttribute Member member, Model model) {


    }*/


    @GetMapping("/members/findPassword")
    public String findP(@ModelAttribute("FindpasswordForm") FindpasswordForm form) {
        return "members/findPassword";
    }
}
