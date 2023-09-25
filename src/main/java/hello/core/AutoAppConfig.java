package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 라는 어노테이션이 붙은 클래스를 다 찾아서 스프링 빈으로 등록해준다.
@ComponentScan(
        // @Configuation이 붙은 ConfigClass들. 예를들어 AppConfig, TestConfig 등을 빼기 위해서
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}

/**
 * 즉, 정리
 * 1. AutoConfig 클래스를 만들고 @ComponentScan 어노테이션을 붙인다.
 * 2. @Component이 붙은 구현 클래스들을 스프링 빈으로 등록한다.
 * 3. 그런데 그렇게 하면 AutoConfig클래스는 비어있는데 의존 주입은 어떡함?
 * 4. 그 때 쓰는게 @Autowired , 자동 의존 주입!
 */
