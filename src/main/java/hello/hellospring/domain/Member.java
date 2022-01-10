package hello.hellospring.domain;

import javax.persistence.*;

@Entity // 이 DTO는 이제부터 JPA가 관리한다는 것을 의미
public class Member {

    // Primary Key는 Long id 이고, 이 값은 Identity로 알아서 DB가 생성해준다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name="SpecialName") // 만약 컬럼명이 변수명과 다르다면 이렇게 직접 지정해주면 된다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
