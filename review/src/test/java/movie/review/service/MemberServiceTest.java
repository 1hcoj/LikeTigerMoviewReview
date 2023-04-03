package movie.review.service;

import movie.review.domain.Member;
import movie.review.domain.qualityStatus;
import movie.review.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    @Test
    @DisplayName("회원 가입 성공 여부 확인")
    void join(){

        //given
        Member member = new Member();
        member.setPhoneNumber("010-123-123");
        member.setName("test");
        member.setQual(qualityStatus.BASIC);

        //when
        Member joinMember = memberService.join(member);

        //then
        Assertions.assertThat(joinMember).isEqualTo(member);



    }

}