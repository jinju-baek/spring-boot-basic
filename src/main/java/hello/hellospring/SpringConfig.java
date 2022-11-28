package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*
    * 1. 의존성 주입(DI)방법
    *   1) 컴포넌트 스캔 방식
    *    @Controller, @Service, @Repository(@ComponentScan 포함)가 해당된다.
    *    @Autowired로 스프링 컨테이너에 등록된 빈을 주입할 수 있다.
    *    정형화된 방식(Controller - Service - Repository)을 사용할 때 주로 사용된다.
    *    생성자 주입(객체 생성 시에 생성되고 값이 변하지 않으므로 추천되는 방식), 필드주입, 세터주입
    *   2) 자바 코드로 직접 bean을 등록하는 방식
    *   @Configuration, @Bean을 통해 사용자가 직접 주입할 수있다.
    *   이 방식으로 생성된 bean은 @Autowired로 주입할 수 없다.
    *   정형화되지 않은 방식에 사용할 때 주로 사용된다.
    *
    * 2. 다형성
    *  다형성이란 소스 수정을 최소화할 수 있다.
    *   ex) B 인터페이스를 구현한 A 구현체가 있다고 가정할 때
    *   C 라는 객체가 A 구현체를 주입받다 새로운 D 객체를 사용하게 된다면
    *   D 에 대한 소스를 새로 추가하고 기존의 A 구현체를 주입한 소스를 모두 수정해줘야 한다.
    *   하지만 B 인터페이스를 주입받는다면 소스 수정없이 D 객체만 주입해주면 된다.
   * */

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
