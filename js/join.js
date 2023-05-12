function joinform_check() {
    const id = document.querySelector(".user_id");
    const pw = document.querySelector(".user_pw");
    const name = document.querySelector(".user_name");
    const phone = document.querySelector(".user_phone");

    if(id.value == "") {
        alert("아이디를 입력하세요.");
        id.focus();
        return false;
    }

    const pwCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

    if(!pwCheck.test(pw.value)) {
        alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야합니다.")
        pw.focus();
        return false;
    }

    if(name.value == "") {
        alert("이름을 입력하세요.");
        name.focus();
        return false;
    }

    if(phone.value == "") {
        alert("전화번호를 입력해주세요.");
        phone.focus();
        return false;
    }


    document.join_form.submit();
}