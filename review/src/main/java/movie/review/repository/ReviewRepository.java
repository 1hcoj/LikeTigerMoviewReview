package movie.review.repository;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    //리뷰 등록
    public Review save(Review review){
        em.persist(review);
        return review;
    }

    //리뷰 삭제
    public void delete(Review review){
        em.remove(review);
    }

    //리뷰 수정
    public Long updateReview(Long id, Review updateReview){
        Review findReview = em.find(Review.class, id);

        //수정은 코맨트나 평점만 가능
        findReview.setComment(updateReview.getComment());
        findReview.setRating(updateReview.getRating());

        return findReview.getId();
    }

    public Review findOne(Long id){
        return em.find(Review.class , id);
    }

    public List<Review> findReviews(){
        return em.createQuery("select r from Review r",Review.class)
                .getResultList();
    }

    public List<Review>  findReviewsWithFetchJoin()
    {
        return em.createQuery("select r from Review r join fetch r.member me join fetch r.movie mo",Review.class)
                .getResultList();
    }
}
