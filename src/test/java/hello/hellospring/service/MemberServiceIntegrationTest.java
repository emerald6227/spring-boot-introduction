package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 각각의 테스트 완료 후에 항상 롤백해주기 때문에 DB에 데이터가 남지 않아서 다음 테스트에 영향이 없다.
class MemberServiceIntegrationTest {

    // 테스트는 다른곳에서 가져다 쓸게 아니므로 편하게 Autowired 하자.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void validateDuplicateMember() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// jupiter 임포트필요
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외 메시지도 체크하기

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}