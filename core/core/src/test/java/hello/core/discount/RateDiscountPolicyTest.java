package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용 되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,20000);
        //then
        assertThat(discount).isEqualTo(2000);
    }
    //자꾸 오류가남. discount값이 1000이 나와야 하는데 0이나옴..
    //discount 계산이 틀린것 같은데.. 일단 인강대로 썼는데 틀려서 내가 다른 방법으로 다시 작성

    @Test
    @DisplayName("VIP가 아니라면 할인을 받아서는 안된다")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberB", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,20000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}