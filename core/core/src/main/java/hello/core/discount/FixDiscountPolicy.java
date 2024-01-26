package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountAccount = 1000; //고정적으로 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) //enum 타입 == 사용
            return discountAccount;
        else
            return 0;
    }
}
