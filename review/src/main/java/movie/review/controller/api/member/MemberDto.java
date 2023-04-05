package movie.review.controller.api.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

    @ApiModelProperty(example = "회원 이름")
    private String name;

    @ApiModelProperty(example = "회원 연락처")
    private String phoneNumber;
    @ApiModelProperty(example = "회원 생년월일")
    private String birthDate;
}
