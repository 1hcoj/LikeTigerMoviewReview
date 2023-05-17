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

    public Optional<Member> login(String loginId , String pw)
    {
        Optional<Member> member_find = memberRepository.findByLoginId(loginId);
        if (member_find.isEmpty()){
            return Optional.empty();
        }
        else{
            Member member = member_find.get();

            if (member.getPassword().equals(pw))
            {

                return Optional.of(member);
            }
            else {
                return Optional.empty();
            }
        }
    }
}
