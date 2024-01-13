package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

//        순수 자바코드가 아닌 sprig 사용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Long memberId = 1L; //member를 미리 정해줌
        Member member = new Member(memberId,"memberA", Grade.VIP); //새로운 멤버 객체 생성 (근데 왜 굳이 mamberId 변수를 따로 만든거지)
        memberService.join(member); //회원 가입

        Order order = orderService.createOrder(memberId, "itemA", 30000);//멤버가 산 아이템 정보 저장

        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
}
