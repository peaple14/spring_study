package mixptc.mixptcservice.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mixptc.mixptcservice.domain.member.Member;
import mixptc.mixptcservice.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/findId")
    public String findI(@ModelAttribute("member") Member member) {
        return "members/findId";

    }


    @GetMapping("/findPassword")
    public String findP(@ModelAttribute("member") Member member) {
        return "members/findPassword";
    }
}
