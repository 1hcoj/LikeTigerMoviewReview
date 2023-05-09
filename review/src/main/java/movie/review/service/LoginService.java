package movie.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.domain.Member;
import movie.review.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId , String pw)
    {
        Member member = memberRepository.findByLoginId(loginId);
        if (member.getPassword().equals(pw))
        {

            return member;
        } else{
            return null;

        }


    }
}
