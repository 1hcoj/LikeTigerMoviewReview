package movie.review.controller.api.member;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateMemberRequestDto {

    @ApiModelProperty(example = "회원 이름")
    private String name;

    @ApiModelProperty(example = "회원 연락처")
    private String phoneNumber;
    @ApiModelProperty(example = "회원 로그인 ID")
    private String loginId;

    @ApiModelProperty(example = "회원 로그인 PW")
    private String password;



}
