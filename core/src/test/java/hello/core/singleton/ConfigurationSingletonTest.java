package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // class hello.core.AppConfig가 출력되어야 할 것 같은데 클래스 명에 xxxCGLIB가 붙어 있음
        // CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속 받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록!
        // 임의의 클래스로 인해 싱글톤 보장 됨
        // 스프링 컨테이너에 이미 등록되어 있으면 있는 거를 꺼내주고 없으면 생성해서 등록하여 반환
        // @Configuration을 붙이면 싱글톤이 보장되지만, @Bean만 적용하면 class hello.core.AppConfig가 출력 되면서 싱글톤이 깨지게 됨
    }
}
