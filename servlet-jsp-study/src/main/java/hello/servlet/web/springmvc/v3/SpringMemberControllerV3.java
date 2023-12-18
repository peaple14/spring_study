package hello.servlet.web.springmvc.v3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/*클래스 단위 -> 메서드 단위
@RequestMapping 클래스 레벨과 메서드 레벨 조합*/

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form",method = RequestMethod.GET)//get으로만 받음 post안됨
    public String newForm() {
        return "new-form";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)//post로만 받음
    public String save(
            //받고
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        //비지니스로직
        Member member = new Member(username, age);
        memberRepository.save(member);
        //모델에담고 리턴
        model.addAttribute("member", member);
        return "save-result";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("mambers", members);
        return "members";
    }
}