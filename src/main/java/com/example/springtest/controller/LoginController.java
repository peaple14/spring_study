package com.example.springtest.controller;

import com.example.springtest.Form.LoginForm;
import com.example.springtest.entity.Member;
import com.example.springtest.repository.MemberRepository;
import com.example.springtest.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("LoginForm") LoginForm form){
        return "LoginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, HttpSession session, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {//이메일 유니크속성 하려했지만 sql쪽 문제이기에 다음에 수정.
            return "redirect:/login";
        }

        if (loginService.checkmember(form.getLogin_Id(), form.getLogin_password())) {
            Member member = memberRepository.findByLoginId(form.getLogin_Id(), form.getLogin_password());
            session.setAttribute("loggedInMember", member);//세션넣어서 리다이렉트해도 유지할수있도록.
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/home")
    public String Home(HttpSession session, Model model){
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        model.addAttribute("id", loggedInMember.getId());
        model.addAttribute("password", loggedInMember.getPassword());
        model.addAttribute("email", loggedInMember.getEmail());
        return "LoginHome";
    }


}
