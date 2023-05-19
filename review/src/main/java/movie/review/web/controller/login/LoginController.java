package movie.review.web.controller.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.domain.Member;
import movie.review.service.LoginService;
import movie.review.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
        return "login/loginForm";
    }

    // @Valid BindingResult 는 해당 id , pw 를 가지는 회원이 없는 경우 오류를 발생시켜야 하기 때문에 넣어야한다.
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm , BindingResult bindingResult , HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Optional<Member> loginMember_find = loginService.login(loginForm.getLoginId(), loginForm.getPassword());


        if (loginMember_find.isPresent()){
            Member loginMember = loginMember_find.get();
            //로그인 성공 처리
            //세션이 있으면 있는 세션을 반환하고 없으면 신규 세션을 생성해서 반환
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보를 보관
            session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
            return "redirect:/";
        }
        else{
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";

        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session !=null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
