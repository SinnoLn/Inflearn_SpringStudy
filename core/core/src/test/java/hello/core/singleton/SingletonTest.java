package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인 (실행할 때 마다 다른 객체가 생성 되는 것을 알 수 있음)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
        //스프링 없는 순수한 DI 컨테이너 AppConfig는 요청을 할 때마다 객체를 새로 생성
        //즉 초당 100개의 고객트래팩이 있다면 100개의 객체가 생성되는것
        //메모리 낭비가 심하므로 객체는 1개 생성되고, 공유하도록 설계하면 된다. - 싱글톤 패턴

    }
        @Test
        @DisplayName("싱글톤 패턴을 적용한 객체 사용")
        void singletonServiceTest() {
            SingletonService singletonService1 = SingletonService.getInstance();
            SingletonService singletonService2 = SingletonService.getInstance();

            //print 해보면 singletonService1과 singletonService2가 같은 객채라는 것을 확인할 수 있다.
            System.out.println("singletonService1 = " + singletonService1);
            System.out.println("singletonService2 = " + singletonService2);

            assertThat(singletonService1).isSameAs(singletonService2);
            //same : java ==
            //equal : java equals
        }

        @Test
        @DisplayName("스프링 컨테이너와 싱글톤")
        void springContainer(){
            ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//            AppConfig appConfig = new AppConfig();
            //1. 조회: 호출할 때 마다 객체를 생성
            MemberService memberService1 = ac.getBean("memberService",MemberService.class);

            //2. 조회: 호출할 때 마다 객체를 생성
            MemberService memberService2 = ac.getBean("memberService",MemberService.class);

            //참조 값이 다른 것을 확인 (실행할 때 마다 다른 객체가 생성 되는 것을 알 수 있음)
            System.out.println("memberService1 = " + memberService1);
            System.out.println("memberService2 = " + memberService2);

            assertThat(memberService1).isSameAs(memberService2);

        }
}
