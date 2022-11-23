package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // afterEach
        // 모든 테스트 메서드를 동시에 실행할 때 메서드들을 실행한 후에 실행하는 메서드이다.
        repository.clearStore();
        // repository의 store를 초기화하여 테스트 시 에러가 발생하지 않도록 한다.
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // test 케이스를 작성할 경우 주로 Assertions.assertThat을 사용한다.
        // system.out.println()이나, Assertions.assertEquals()의 방법도 있지만 위의 방법을 더 추천한다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // 같은 이름의 변수를 한번에 변경하고 싶을 때는 "shift + f6"
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
