package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId , String pw)
    {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        Member member = findMember.get();
        if (member.getPassword().equals(pw))
        {
            return member;
        }
        return null;
    }
}
