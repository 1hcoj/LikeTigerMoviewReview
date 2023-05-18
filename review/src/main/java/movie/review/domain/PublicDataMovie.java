package movie.review.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class PublicDataMovie {

    @Id @GeneratedValue
    @Column(name="public_data_movie_id")
    private Long id;

    //Open API 영화 코드
    private String movieCd;

    private String title;

}
