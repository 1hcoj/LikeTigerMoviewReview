package movie.review.web.controller.member;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        MemberForm form = new MemberForm();
        model.addAttribute("memberForm",form);
        return "members/createMemberForm";
    }

    //여기에 유효성 검사를 추가하려면 @Valid , BindingResult 사용해서 추가하면 되긴함
    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        Member member = new Member();
        createMember(memberForm, member);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String findMembers(Model model)
    {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    private static void createMember(MemberForm memberForm, Member member) {
        member.setLoginId(memberForm.getLoginId());
        member.setPhoneNumber(memberForm.getPhoneNumber());
        member.setPassword(memberForm.getPassword());
        member.setName(memberForm.getName());
    }
}
