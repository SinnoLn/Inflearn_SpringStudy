package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository ----------------- 실제로는 이렇게 호출 안됨

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService ------------------- 이렇게 호출됨. 즉 spring가 singleton을 보장해 준다는 것을 알 수 있음.

    @Bean //Spring Container 에 등록됨
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    //여기 static을 붙이면 안됨. test CongifurationSingletonTest에서 객체가 서로 다르게 나옴
    //왜인지는 정확히 확인해봐야할듯

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl( memberRepository(),discountPolicy());
    }
    //여기서 FixDiscountPolicy 라고 돼 있었음.그래서 자꾸 1000원 할인됨

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    //할인 정책을 고쳐도 이거 하나만 고치면 된다.
}
