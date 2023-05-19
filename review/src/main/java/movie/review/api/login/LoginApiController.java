package movie.review.api.login;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.service.LoginService;
import movie.review.web.SessionConst;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = {"MovieReviewProject Login API"})
public class LoginApiController {

    private final LoginService loginService;

    //세션 관리?


    /**
     * 세션을 통한 로그인 , 리뷰도 전문가와 네티즌을 나눠서 남기기 , ...
     */

    @PostMapping("api/login")
    public LoginApiResponseDto login(LoginApiRequestDto request){
        Optional<Member> loginMember_find = loginService.login(request.getLoginId(), request.getPassword());

        if (loginMember_find.isPresent()){
            Member loginMember = loginMember_find.get();
            //로그인 성공 처리
            //세션이 있으면 있는 세션을 반환하고 없으면 신규 세션을 생성해서 반환
            String token = UUID.randomUUID().toString();
            return new LoginApiResponseDto(loginMember.getName(),token);
        }
        else{
            return new LoginApiResponseDto("실패","실패");
        }
    }
}
