package movie.review.repository;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public void delete(Member member){
        em.remove(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        List<Member> all = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return all;
    }

    public Long updateMember(Long id,Member updateMember){
        Member findMember = em.find(Member.class, id);
        findMember.setName(updateMember.getName());
        findMember.setPhoneNumber(updateMember.getPhoneNumber());

        return findMember.getId();
    }

    //검색조건이 만약 있다면 findByName 같은 메소드도 만들기

    //회원의 로그인 ID 로 찾는 경우
    public Optional<Member> findByLoginId(String loginId){
        List<Member> all = findAll();
        for (Member member : all) {
            if (member.getLoginId().equals(loginId)){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}
