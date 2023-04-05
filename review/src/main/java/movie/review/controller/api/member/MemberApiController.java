package movie.review.controller.api.member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import movie.review.controller.api.Result;
import movie.review.domain.Member;
import movie.review.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = {"MovieReviewProject Member API"})
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("api/members") //회원가입
    @ApiOperation(value = "회원 가입" , notes = "회원 가입 API")
    public CreateMemberResponseDto saveMember(@RequestBody CreateMemberRequestDto request)
    {
        Member member = new Member();
        Member newMember = createMemberSetting(request, member);
        memberService.join(newMember);
        return new CreateMemberResponseDto(newMember.getId());
    }

    @PutMapping("api/members/{id}") //회원 수정
    @ApiOperation(value = "회원 정보 수정" , notes = "회원 정보 수정 API")
    public UpdateMemberResponseDto updateMember(@PathVariable("id") Long id , @RequestBody UpdateMemberRequestDto request)
    {
        Member member = new Member();
        updateMemberSetting(request, member);
        Member updateMember = memberService.update(id, member);
        return new UpdateMemberResponseDto(updateMember.getId(), updateMember.getName(), updateMember.getPhoneNumber(), updateMember.getBirthDate());
    }

    @GetMapping("api/members")
    @ApiOperation(value = "모든 회원 조회" , notes = "모든 회원 조회 API")
    public Result members()
    {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getPhoneNumber(), m.getBirthDate()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    private static void updateMemberSetting(UpdateMemberRequestDto request, Member member) {
        member.setName(request.getName());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setBirthDate(request.getBirthDate());
    }





    private static Member createMemberSetting(CreateMemberRequestDto request, Member member) {
        member.setName(request.getName());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setLoginId(request.getLoginId());
        member.setPassword(request.getPassword());
        member.setBirthDate(request.getBirthDate());
        return member;
    }


}
