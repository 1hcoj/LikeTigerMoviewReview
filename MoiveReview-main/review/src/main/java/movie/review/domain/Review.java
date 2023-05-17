package movie.review.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Review {

    @Id @GeneratedValue
    @Column(name="review_id")
    private Long id;

    private int rating; //점수

    private String comment; //한줄평

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="movie_id")
    private Movie movie;

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    //연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    //연관관계 편의 메서드
    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getReviews().add(this);
    }
}
