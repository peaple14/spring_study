package mixptc.mixptcservice.domain.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    public static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }


    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))//멤버의loginid와 적은loginid가 같을때 다음단계로 이동
                .findFirst();
    }

    public Optional<Member> findByTel(Long tel) {
        return findAll().stream()
                .filter(m -> m.getTel().equals(tel))
                .findFirst();
    }


    public void clearStore(){
        store.clear();
    }
}
