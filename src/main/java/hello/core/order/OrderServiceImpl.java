package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 매개변수를 가지고 생성자를 만들어주는 lombok annotation
public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /**
     * OCP 위반 : RateDiscountPolicy를 추가했다고 해서 serviceImpl의 코드를 고치는 상황이 나와서는 안됨
     * DIP 위반 : ServiceImpl의 코드를 보면, abstract한 interface인 DiscountPolicy뿐만 아니라 Fix/Rate DiscountPolicy에도 의존하고 있음을 확인할 수 있음.
     * solution : 구현체가 아닌, 추상 인터페이스에만 의존하도록 해야함
     */
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // @Autowired private MemberRepository memberRepository;
    // 필드 주입 : 코드는 간결하지만 외부에서 변경이 불가능해서 안티패턴임
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 변경 가능성 있으면 required=false 옵션을 줄 수 있음
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    // 생성자가 하나일 때는 자동으로 @Autowired가 적용됨
    // 웬만하면 생성자 주입을 이용하자
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

//    // 일반 메서드 주입 (여러 필드를 한 번에 받을 수 있음, 일반적으로 잘 사용하지 않음)
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("OrderServiceImpl.init");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // Test용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
