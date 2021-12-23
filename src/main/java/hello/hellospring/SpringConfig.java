package hello.hellospring;

import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링이 실행될 때 빈으로 등록할 설정 내용을 정의한다. (이번 프로젝트 한정 레포지토리 -> DB레포지토리로 변경 예정이므로 이걸 사용)
public class SpringConfig {

    @Bean // 빈으로 정의해주고, 처음에 등록될 수 있도록 함
    public MemberService memberService() {
        return new MemberService(memoryMemberRepository());
    }

    @Bean
    public MemoryMemberRepository memoryMemberRepository() { // 인터페이스가아닌 구현체로 해야함
        return new MemoryMemberRepository();
    }
}