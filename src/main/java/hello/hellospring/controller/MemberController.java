package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨트롤러 애너테이션을 추가했으니 이제 스프링 컨테이너에서 객체를 생성하고 관리한다.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private final MemberService memberService; // 위 처럼 new로 생성하지않고 의존성 주입을 받자

    @Autowired // 스프링 컨테이너에 등록된 스프링빈(MemberService)과 연결시켜주기위해 사용
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
