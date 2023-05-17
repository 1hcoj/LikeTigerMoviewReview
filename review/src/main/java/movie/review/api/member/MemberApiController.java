package movie.review.api.member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.api.Result;
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


    //이 API 는 마이페이지에서 사용할 것 같음
    @PutMapping("api/members/{id}") //회원 수정
    @ApiOperation(value = "회원 정보 수정" , notes = "회원 정보 수정 API")
    public UpdateMemberResponseDto updateMember(@PathVariable("id") Long id , @RequestBody UpdateMemberRequestDto request)
    {
        // 커맨드와 쿼리를 분리한다는 정책을 준수하면서 그냥 update 는 커맨드성으로 끝내고
        memberService.update(id, request.getPhoneNumber() , request.getPassword());

        // .findOne 쿼리성으로 다시 조회해서 변경된 회원을 가져오는 로직으로 사용
        Member findMember = memberService.findOne(id);

        return new UpdateMemberResponseDto(findMember.getId(), findMember.getName());
    }

    //이거는 사용할수도 있고 사용 안 할 수도 있는데 만약을 위해서 만들어놓음
    @GetMapping("api/members")
    @ApiOperation(value = "모든 회원 조회" , notes = "모든 회원 조회 API")
    public Result members()
    {
        List<Member> findMembers = memberService.findMembers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getReviews()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    private static Member createMemberSetting(CreateMemberRequestDto request, Member member) {
        member.setName(request.getName());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setLoginId(request.getLoginId());
        member.setPassword(request.getPassword());
        return member;
    }


}
