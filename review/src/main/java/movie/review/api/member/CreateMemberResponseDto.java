package movie.review.api.member;

import lombok.Data;

@Data
public class CreateMemberResponseDto {

    //응답 값은 member 식별자 값 반환?
    private Long id;

    //

    public CreateMemberResponseDto(Long id) {
        this.id = id;
    }
}
