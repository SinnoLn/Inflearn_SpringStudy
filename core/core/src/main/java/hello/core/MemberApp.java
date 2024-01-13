package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//회원 도메인
//회원가입 main
public class MemberApp {
    public static void main(String[] args) {

//        스프링 사용을 위해 순수 자바코드 주석처리
//        직접적이고 명시적이지만, 의존성을 수동으로 관리해야 한다는 단점이 있음
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

//        스프링 프레임워크 기능을 사용하여 의존성을 관리
//        ApplicationContext는 spring의 ioc컨테이너로 애플리케이션의 구성 요소들과 생명주기를 관리
//        AnnotationConfigApplicationContextsm는  AppConfig 클래스에 정의된 설정 정보를 사용하여 컨테이너를 초기화
//        getbin메서드를 통해 필요한 빈 객체를 요청하고, 스프링은 이 객체를 찾아서 반환, 구성요소간의 결합도를 낮추고,
//        유연한 의존성 관리를 가능하게 함

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        /*
        * //생성자, 객체 생성 시 설정
        public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        }*/

        // 임시로 멤버를 삽입
        Member member1 = new Member(1L, "memberA", Grade.VIP); //Long id -> id"L"붙여줘야 함
        Member member2 = new Member(2L, "memberB", Grade.BASIC);
        memberService.join(member1); //멤버를 Map에 저장<Key, Data> MemberService -> MemberRepository
        memberService.join(member2);

        Member findMember = memberService.findMember(1L); // id=1의 memberA를 찾아볼거임
        System.out.println("newMember = " + member2.getName()); //field가 private이기 때문에 get set 메소드를 이용해서 가지고 와야함
        System.out.println("findMember = " + findMember.getName()); //soutv -> 변수값을 간단하게 출력해볼 수 있음

    }
}

//순수 자바 코드는 의존성 관리를 수동으로 처리해야 하지만,
//스프링 프레임워크를 사용하면 이러한 의존성 관리가 자동화되고,
//개발자는 비즈니스 로직에 더 집중할 수 있음.