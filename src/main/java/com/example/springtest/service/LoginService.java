package com.example.springtest.service;

import com.example.springtest.entity.Member;
import com.example.springtest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public boolean checkmember(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId, password);
        if (member != null && member.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }





}
