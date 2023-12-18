package com.example.springtest.controller;

import com.example.springtest.entity.Member;
import com.example.springtest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewAcController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/newmember")
    public String newMember(Model model){
        model.addAttribute("member", new Member());
        return "/newmem/NewMember";
    }

    @PostMapping("/newmember")
    public String membersave(@ModelAttribute Member member){
        //비어있거나 ""이 들어갈시.
        if (member == null || member.getPassword() == null || member.getId() == null || member.getEmail() == null || member.getId().trim().isEmpty() || member.getPassword().trim().isEmpty() || member.getEmail().trim().isEmpty()) {
            return "redirect:/newmember?error";
        }
        System.out.println("회원추가 완료");
        memberRepository.save(member);
        System.out.println(member);
        return "redirect:/login";
    }

}
