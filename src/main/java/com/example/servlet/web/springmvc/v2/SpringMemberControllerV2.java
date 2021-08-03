package com.example.servlet.web.springmvc.v2;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/save")
    public ModelAndView save(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username , age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member" , member);
        return mv;
    }

    @RequestMapping("/new-form")
    public ModelAndView toForm(){
        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form");
    }



    @RequestMapping("")
    public ModelAndView showList() {
        List<Member> members= memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members" , members);
        return mv;
    }
}
