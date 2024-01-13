package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    //인터페이스에 객체 연결
    //단일체계의 원칙이 잘 지켜진 사례
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        
        Long memberId = 1L; //근데 왜 그냥 1L을 바로 넣지 않고 굳이 변수를 만들어서 대입하지? 
        //1L이라도 그냥 써도 상관 없는것 같은데 new Member와 createOrder에 같이 들어가니까 그런듯
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //만약 할인된 가격이 1000원이 아니라면 test 실패 (itemA 가격 10000 -> 10%할인 됐으니까 1000원)
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
