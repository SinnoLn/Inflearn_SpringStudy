package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //MemberServiceImpl은 인터페이스 MemberRepository를 의존하고 구현 객체인 MemoryMemberRepository()도 의존함
    //추상체와 구현체에 모두 의존하고 있으므로 DIP위반
    private final MemberRepository memberRepository;

    @Autowired //의존관게 자동으로 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    ///테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
