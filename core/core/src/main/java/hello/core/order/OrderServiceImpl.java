package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//여기는 rate discount policy가 없는데 의존성 때문에 후 강의에서 뺐나봄. 일단은 파악
//이 클라이언트는 DiscountPolicy 인터페이스 뿐만 아니라 (비율로 할인)RateDiscountPolicy란 구현객체에도 의존하고 있다. - DIP위반
//FixDiscountPolicy를 RateDiscountPolicy로 바꾸는 순간 OrderServiceImpl의 소스코드도 함께 교체해 줘야 한다 - OCP위반
@Component
@RequiredArgsConstructor //Lombok의 Annotation (필수값인 final이 붙은것을 가지고 생성자를 만들어줌) (이걸 많이 사용함!!!!)
public class OrderServiceImpl implements OrderService{

    //이 문제를 해결하기 위해서는 누군가 클라이언트 OrderServiceImpl에 DiscountPolicy의 구현객체를 대신 생성하고 대입해 주어야 한다.

    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); 원래 코드 그러나 의존성 문제가 발생한다.(DIP위반)
    //인터페이스에만 의존하도록 코드를 변경해 주어야 한다.
    private final DiscountPolicy discountPolicy; //final을 사용하면 위처럼 값이 할당되어야 하므로 선언만 해줌
    private final MemberRepository memberRepository;
    //@RequiredArgsConstructor 를 사용하면 나중에 final을 추가할 때 굉장히 편리하다

//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    //생성자
    //MemoryMemberRepository와 FixDiscountPolicy 객체의 의존관계가 주입됨.
    @Autowired //생성자가 1개만 있다면 생략해도 된다.
    public OrderServiceImpl( MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl"); //soutm
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //단일체계의 원칙이 잘 지켜진 사례
        //가격정책을 바꾸는것과 데이터를 저장하는 부분등 가변적 요소가 있는 각 기능이 완전히 독립됨.
        Member member = memberRepository.findById(memberId); //memberId를 통해 멤버 이름 가져옴
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    ///테스트 용도 (지금 어떤 레포지토리를 사용하고 있는지 알려줌)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
