package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//읽기와 쓰기중 어떤게 주인가에 따라 @Transactional을 어떻게 쓸지 달라짐
//javax와 spring이 제공하는 transactional중 spring이 제공하는 어노테이션을 쓰자
@Service
@Transactional(readOnly = true) //JAP의 변경이나 로직들은 가급적이면 트랜젝션 안에서 실행되어야 한다.
//@AllArgsConstructor(필드 모든걸 가지고 생성자를 만들어줌)
@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자를 만들어줌(더 나은 방법)
public class MemberService {

    //생성자 injection은 final 추천, 컴파일 시점에 값이 들어갔는지 체크 가능
    private final MemberRepository memberRepository;

   //생성자 injection (test case시 내가 직접 주입을 해줘야 함)
   //생성자가 하나만 있는 경우 Autowired 어노테이션이 없어도 자동 injection
/*    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //애플리케이션 실행 시점에 모든 조립이 끝나므로 setter injection도 별로 좋지 않음
  /*  @Autowired
    //Setter Injection (애플리케이션이 돌아가는 시점에 누군가가 이걸 바꿀 수 있음)
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/
    /**
     * 회원 가입
     */
    @Transactional //읽기가 아닌 쓰기므로 readOnly = true를 넣으면 안됨
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검색
        memberRepository.save(member);
        return member.getId();
        //이런 형태로 꺼내면 항상 값이 있다는게 보장됨
    }

    //더 최적화 하고 싶으면 멤버 수를 세어서 개수가 1 이상이면 EXCEPTION
    //동시에 a라는 이름으로 가입을 시도하면 EXCEPTION이 무시될 수 있음
    //DB에서 attribute를 unique로 잡아주는걸 권장
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
    //@Transactional(readOnly = true) 트랜젝션을 조회하는 곳에서는 좀 더 성능 최적화 함
    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한명만 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
