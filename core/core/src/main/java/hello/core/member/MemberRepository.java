package hello.core.member;

//회원 저장소 interface
//Member를 저장하고 id로 검색함
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
