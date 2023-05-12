function addform_check() {
    const title = document.querySelector(".add-movie__title");
    const director = document.querySelector(".add-movie__director");
    const actor = document.querySelector(".add-movie__actor");
    const time = document.querySelector(".add-movie__time");
    const grade = document.querySelector(".add-movie__grade");
    const content = document.querySelector(".add-movie__content");

    if(title.value == "") {
        alert("제목을 입력하세요.");
        title.focus();
        return false;
    }

    if(director.value == "") {
        alert("감독이름을 입력하세요.");
        director.focus();
        return false;
    }
    if(actor.value == "") {
        alert("배우를 입력하세요.");
        actor.focus();
        return false;
    }

    if(time.value == "") {
        alert("시간을 입력하세요.");
        time.focus();
        return false;
    }

    if(time.value <= 0) {
        alert("올바르지 않은 시간 형식입니다. 다시 입력해주세요.");
        time.focus();
        return false;
    }

    if(grade.value == "") {
        alert("내용을 입력하세요.");
        grade.focus();
        return false;
    }

    if(content.value == "") {
        alert("내용을 입력하세요.");
        content.focus();
        return false;
    }
    

    

    document.movie_add_form.submit();
}

