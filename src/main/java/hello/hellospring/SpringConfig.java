package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration // 스프링이 실행될 때 빈으로 등록할 설정 내용을 정의한다. (이번 프로젝트 한정 레포지토리 -> DB레포지토리로 변경 예정이므로 이걸 사용)
public class SpringConfig {

    // @PersistenceContext // 원래 기존 스펙에서는 이렇게 주입받아야하는데 아래처럼 이번에는 그냥 스프링에서 주입받아 사용하자
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    아래는 JDBC를 사용할 때 필요했던 DataSource를 주입받는 부분
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean // 빈으로 정의해주고, 처음에 등록될 수 있도록 함
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { // 인터페이스가아닌 구현체로 해야함
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource); // Jdbc로 갈아끼움
        return new JpaMemberRepository(em); // JPA로 갈아끼움
    }
}