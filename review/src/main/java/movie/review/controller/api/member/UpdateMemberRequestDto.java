package movie.review.controller.api.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateMemberRequestDto {

    @ApiModelProperty(example = "수정할 회원 이름")
    private String name;

    @ApiModelProperty(example = "수정할 회원 연락처")
    private String phoneNumber;

    @ApiModelProperty(example = "수정할 회원 생년월일")
    private String birthDate;

}
