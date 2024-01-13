package hello.core.member;

//회원 서비스에 대한 인터페이스
//회원가입과 회원찾기인가
public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
