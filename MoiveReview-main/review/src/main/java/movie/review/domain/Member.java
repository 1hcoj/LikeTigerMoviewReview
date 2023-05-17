package movie.review.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;     //pk

    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private qualityStatus qual;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    private String loginId; //로그인 아이디

    private String password; //로그인 패스워드
}
