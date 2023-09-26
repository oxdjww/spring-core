package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 라는 어노테이션이 붙은 클래스를 다 찾아서 스프링 빈으로 등록해준다.
@ComponentScan(
        // basePackage를 지정하지 않으면 파일 상단 dir부터. 즉, hello.core부터~
//        basePackages = "hello.core.member",
        // @Configuation이 붙은 ConfigClass들. 예를들어 AppConfig, TestConfig 등을 빼기 위해서
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 아래와 같은 bean을 수동으로 등록하면, CoreApplication 실행 시 오류가 뜬다
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}

/**
 * 즉, 정리
 * 1. AutoConfig 클래스를 만들고 @ComponentScan 어노테이션을 붙인다.
 * 2. @Component이 붙은 구현 클래스들을 스프링 빈으로 등록한다.
 * 3. 그런데 그렇게 하면 AutoConfig클래스는 비어있는데 의존 주입은 어떡함?
 * 4. 그 때 쓰는게 @Autowired , 자동 의존 주입!
 */
