package mixptc.mixptcservice.web;

import mixptc.mixptcservice.domain.member.Member;
import mixptc.mixptcservice.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Login Member loginMember, Model model) {
        if (loginMember == null) {//세션에 데이터없으면 home
            return "home";
        }
        model.addAttribute("member", loginMember);//세션유지시 로그인
        return "loginHome";
    }


}



