function addform_check() {
    const title = document.querySelector(".add-review__title");
    const writer = document.querySelector(".add-review__writer");
    const score = document.querySelector(".add-review__score");
    const content = document.querySelector(".add-review__content");

    if(title.value == "") {
        alert("리뷰 제목을 입력하세요.");
        title.focus();
        return false;
    }
    if(writer.value == "") {
        alert("리뷰 작성자를 입력하세요.");
        writer.focus();
        return false;
    }
    if(score.value == "") {
        alert("리뷰 점수를 입력하세요.");
        score.focus();
        return false;
    }
    if(score.value > 10 || score.value <0) {
        alert("점수는 0 ~ 10 만 가능합니다.");
        score.focus();
        return false;
    }

    if(content.value == "") {
        alert("리뷰 내용을 입력하세요.");
        content.focus();
        return false;
    }

    document.review_add_form.submit();
}
