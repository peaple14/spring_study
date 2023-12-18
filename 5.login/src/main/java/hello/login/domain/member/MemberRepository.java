package hello.login.domain.member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //동시성 고려x
    private static long sequence = 0L;
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()//리스트를 stream으로 바꾸고
                .filter(m -> m.getLoginId().equals(loginId))//만족하는 애만 거르고 만족하는 애만 다음단계로
                .findFirst();//먼저 나온애만 다음으로
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}