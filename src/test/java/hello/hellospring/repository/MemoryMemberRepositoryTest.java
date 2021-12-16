package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions; // jupiter 라이브러리도 사용가능하긴함
import org.assertj.core.api.Assertions; // 요즘에는 이 라이브러리를 편해서 더 많이 씀
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // assertj를 static으로 import해서 축약이 가능해짐 (option + enter)

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 아래 테스트 메서드가 실행이 끝날 때마다 이 함수를 실행하도록 한다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional 자료형은 .get()으로 바로 꺼낼 수 있음
//        System.out.println("result = " + (result == member)); // 일일이 text로 확인하는건 힘들어...

//        Assertions.assertEquals(member, result); // jupiter 라이브러리
        assertThat(member).isEqualTo(result); // (추천)assertj 라이브러리
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
