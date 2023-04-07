package movie.review.controller.api.review;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import movie.review.controller.api.Result;
import movie.review.domain.Movie;
import movie.review.domain.Review;
import movie.review.repository.MovieRepository;
import movie.review.repository.ReviewRepository;
import movie.review.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = {"MovieReviewProject Review API"})
public class ReviewApiController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @PostMapping("api/{id}/reviews")
    @ApiOperation(value = "리뷰 등록" , notes = "리뷰 등록 API")
    public CreateReviewResponseDto saveReview(@PathVariable("id") Long id, @RequestBody CreateReviewRequestDto request)
    {
        Movie findMovie = movieRepository.findOne(id);
        Review review = new Review();
        createReviewSetting(request, review);
        reviewService.addReview(review);
        review.setMovie(findMovie);
        return new CreateReviewResponseDto(review.getId(), request.getComment());
    }

    @PutMapping("api/reviews/{id}")
    @ApiOperation(value = "리뷰 수정" , notes = "리뷰 수정 API")
    public UpdateReviewResponseDto updateReview(@PathVariable("id") Long id , @RequestBody UpdateReviewRequestDto request)
    {
        Review review = new Review();
        updateReviewSetting(request, review);
        Review updateReview = reviewService.updateReview(id, review);
        return new UpdateReviewResponseDto(updateReview.getId());
    }

    @GetMapping("api/reviews")
    @ApiOperation(value = "모든 리뷰 조회" , notes = "모든 리뷰 조회 API")
    public Result reviews()
    {
        List<Review> result = reviewRepository.findReviewsWithFetchJoin();
        List<ReviewDto> collect = result.stream().map(r -> new ReviewDto(r))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    private static void updateReviewSetting(UpdateReviewRequestDto request, Review review) {
        review.setComment(request.getComment());
        review.setRating(request.getRating());
    }

    private static void createReviewSetting(CreateReviewRequestDto request, Review review) {
        review.setRating(request.getRating());
        review.setComment(request.getComment());
    }
}
