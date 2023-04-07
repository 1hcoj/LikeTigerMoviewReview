package movie.review.controller.api.member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.controller.api.Result;
import movie.review.domain.Member;
import movie.review.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = {"MovieReviewProject Member API"})
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("api/members") //회원가입
    @ApiOperation(value = "회원 가입" , notes = "회원 가입 API")
    public CreateMemberResponseDto saveMember(@RequestBody CreateMemberRequestDto request)
    {

        // 여기에 넘어온 ID,PW 의 정보가 알맞은건지 필터? 인터셉터? 세션 으로 회원가입 상태 유지 시켜야함
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
