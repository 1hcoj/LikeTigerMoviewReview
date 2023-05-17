package movie.review.api.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateMemberRequestDto {

    @ApiModelProperty(example = "수정할 회원 연락처")
    private String phoneNumber;

    @ApiModelProperty(example = "수정할 회원 비밀번호")
    private String password;
}
