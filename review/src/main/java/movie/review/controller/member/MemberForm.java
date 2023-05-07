package movie.review.controller.member;

import lombok.Data;

@Data
public class MemberForm {

    private String name;
    private String loginId;

    private String password;
    private String phoneNumber;
}
