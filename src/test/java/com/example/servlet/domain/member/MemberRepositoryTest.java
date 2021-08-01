package com.example.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member id = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(id).isEqualTo(member);
    }

    @Test
    void findAll(){
        //given
        Member hi1 = new Member("hi", 1);
        Member hi2 = new Member("hi", 2);

        memberRepository.save(hi1);
        memberRepository.save(hi2);

        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(hi1, hi2);
    }
}