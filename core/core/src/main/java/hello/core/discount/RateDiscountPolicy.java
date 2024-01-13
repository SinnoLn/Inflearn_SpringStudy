package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    //비용의 10%를 할인해 줌
    private float discountAccount = 0.1f;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) //enum은 비교시 ==을 사용 한다.
            return (int)(price*discountAccount);
        else
            return 0;
    }
}
