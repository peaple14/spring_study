package com.example.springtest.controller;

import com.example.springtest.Form.FindIdForm;
import com.example.springtest.Form.FindPasswordForm;
import com.example.springtest.controller.dto.MailDto;
import com.example.springtest.repository.MemberRepository;
import com.example.springtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class FindController {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private final EmailService emailService;


    @GetMapping("/findId")
    public String findI(@ModelAttribute("FindIdForm") FindIdForm form) {
        return "find/findId";
    }

    @PostMapping("/findId")
    public String FindI(@Validated @ModelAttribute("FindIdForm") FindIdForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { //오류날시
            return "find/findId";
        }

        if (form == null ||  form.getFindid_email() == null|| form.getFindid_email().trim().isEmpty() ) {
            return "redirect:/findId?error";
        }

        String email = form.getFindid_email();
        String foundMember = memberRepository.findIdByEmail(email);
        System.out.println(foundMember);

        if (foundMember != null) {
            model.addAttribute("foundId", foundMember);
            return "/find/findIdResult";
        } else {
            return "redirect:/findId";
        }
    }


    @GetMapping("/find/findIdResult")
    public String findIdResult(Model model, @RequestParam("foundId") String foundId) {
        model.addAttribute("foundId", foundId);
        return "find/findIdResult";

    }

    @GetMapping("/findpassword")
    public String findP(@ModelAttribute("FindPasswordForm") FindPasswordForm form) {
        return "find/findpassword";
    }

    @PostMapping("/findpassword")
    public String FindP(@Validated @ModelAttribute("FindIdForm") FindPasswordForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "find/findpassword";
        }

        if (form == null || (form.getFindpassword_Id() == null || form.getFindpassword_Id().trim().isEmpty()) || (form.getFindpassword_email() == null || form.getFindpassword_email().trim().isEmpty())) {
            return "redirect:/findpassword?error";
        }


        String id = form.getFindpassword_Id();
        String email = form.getFindpassword_email();
        String foundpassword = memberRepository.findPasswordByIdAndEmail(id,email);


        MailDto mailDto = new MailDto();
        mailDto.setTitle("조모씨의 비번찾기");
        mailDto.setAddress(email);
        mailDto.setContent("찾으신 비밀번호는 : "+ foundpassword + "입니다.");

        System.out.println(mailDto);


        if (foundpassword != null) {
            emailService.sendSimpleMessage(mailDto);
            return "/find/findpasswordresult";
        } else {
            return "redirect:/findpassword";
        }
    }


}
