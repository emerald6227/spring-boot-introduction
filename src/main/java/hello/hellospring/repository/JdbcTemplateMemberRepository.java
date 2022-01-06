package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements  MemberRepository {

    // jdbc템플릿이라고 부르는 이유는 디자인패턴중에 '템플릿 메서드 패턴'이 많이 반영되어 있음 (코드를 줄일 수 있다.)
    private final JdbcTemplate jdbcTemplate;

    @Autowired // 생성자가 딱 1개이면 Autowired 생략이 가능함 (이번엔 그냥 쓰자)
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // insert 문을 만드는 부분
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); // 테이블명, Primary key

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // 만들어진 쿼리를 실행하고 반환받는 부분
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny(); // Optional 자료형이라서 이렇게 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = jdbcTemplate.query("select * from member", memberRowMapper());
        return result;
    }

    private RowMapper<Member> memberRowMapper() {
//        return new RowMapper<Member>() { // option + enter 키로 람다 변경 가능
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return member;
//            }
//        };

        // 위 함수를 람다로 바꾼 것
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
