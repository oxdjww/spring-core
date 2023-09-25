package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemoryMemberRepository.class);

        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        MemberRepository memberRepository1 = orderService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();
        
        assertThat(memberRepository1).isSameAs(memberRepository2);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }
    
    @Test
    void configurationDeep() {
        /**
         * \@Configuration이 AppConfig class에 있기 때문에 test결과는 AppConfig를 상속받은 AppConfigCGLIB 클래스임
         * 그 임의의 *CGLIB 클래스가 스프링 빈이 싱글톤패턴을 유지하게끔 도와준다.
         * AppConfig class의 \@Configuration을 지우고 테스트하면
         * call ~~ 문이 출력된다  . -> 싱글톤 패턴이 깨진 것을 볼 수 있음
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
