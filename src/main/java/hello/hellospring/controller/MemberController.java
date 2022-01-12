package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컨트롤러 애너테이션을 추가했으니 이제 스프링 컨테이너에서 객체를 생성하고 관리한다.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private final MemberService memberService; // 위 처럼 new로 생성하지않고 의존성 주입을 받자

    @Autowired // 스프링 컨테이너에 등록된 스프링빈(MemberService)과 연결시켜주기위해 사용
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass()); // Proxy 주입이 되는지 콘솔창에 출력해보기
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // (프론트에서 사용할 이름, 자바 변수)
        return "members/memberList";
    }
}
