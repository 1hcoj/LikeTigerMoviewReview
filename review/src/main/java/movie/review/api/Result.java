package movie.review.api;

import lombok.AllArgsConstructor;
import lombok.Data;



//유연성 있는 API 설계를 위해 Result 로 감싸서 반환해야 한다.
//이렇게 해야 API 스펙에 회원 수 같은 것을 추가하기 쉽다.
@Data
@AllArgsConstructor
public class Result<T> {
    private T data;
}
