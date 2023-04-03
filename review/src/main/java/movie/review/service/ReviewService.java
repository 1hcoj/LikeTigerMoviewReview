package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Review;
import movie.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //리뷰 등록
    @Transactional
    public Review addReview(Review review){
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(Long id , Review updateReview){
        Review findReview = reviewRepository.findOne(id);

        findReview.setRating(updateReview.getRating());
        findReview.setComment(updateReview.getComment());
        findReview.setMovie(updateReview.getMovie());

        return findReview;
    }

    //리뷰 읽어오기 (한 개)
    public Review findOne(Long id){
        return reviewRepository.findOne(id);
    }

    public List<Review> findAll(){
        return reviewRepository.findReviews();
    }


}
