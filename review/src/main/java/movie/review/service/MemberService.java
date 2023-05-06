package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 등록
    @Transactional
    public Member join(Member member){
        return memberRepository.save(member);
    }

    //회원 수정
    @Transactional
    public void update(Long id , String phoneNumber , String password){
        Member member = memberRepository.findOne(id);
        member.setPhoneNumber(phoneNumber);
        member.setPassword(password);
    }

    //회원 삭제
    public void delete(Member member){
        memberRepository.delete(member);
    }

    //회원 읽어오기 (한 명)
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

    //회원 읽어오기 (전부)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
