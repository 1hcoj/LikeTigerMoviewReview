package movie.review.controller.api.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateMemberResponseDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String birthDate;
}
