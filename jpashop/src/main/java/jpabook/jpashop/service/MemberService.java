package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검색
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
    }

    //회원 전체 조회
}
