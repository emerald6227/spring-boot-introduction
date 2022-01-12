package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // JpaRepository<DTO객체, id값 이름>

    // JPQL : select m from Member m where m.name = ? 을 자동으로 jpa가 만들어줌
    @Override
    Optional<Member> findByName(String name); // 사실 이미 MemberRepository 를 상속 받았기 때문에 작성 안해도 JPA가 알아서 구현해줌

//     예시2) name과 id가 조건이라면 And로 id를 추가로 네이밍해주면 알아서 쿼리 생성
//     JPQL : select m from Member m where m.name = ? and m.id = ? 를 자동으로 jpa가 만들어줌
//    Optional<Member> findByNameAndId(String name, Long id);
}
