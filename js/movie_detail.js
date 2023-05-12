API_KEY = "753acb6c51f1a37780991d21607a94b4";

async function getData() {
    const url = `http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=${API_KEY}&movieCd=2023C165`;

    const response = await fetch(url);
    const data = await response.json();
    console.log("data", data);

    const title = document.querySelector(".movie-detail__title");
    const director = document.querySelector(".movie-detail__director");
    const actor = document.querySelector(".movie-detail__actor");
    const time = document.querySelector(".movie-detail__time");
    const grade = document.querySelector(".movie-detail__grade");
    const content = document.querySelector(".movie-detail__content");

    let title_H1Tag = document.createElement('h1');
    title_H1Tag.setAttribute('class', 'h1Tag');
    title_H1Tag.innerHTML = data.movieInfoResult.movieInfo.movieNm;
    title.appendChild(title_H1Tag);
    //title.innerText = data.movieInfoResult.movieInfo.movieNm;
    director.innerText = "감독 : " + data.movieInfoResult.movieInfo.directors[0].peopleNm;
    actor.innerText = "주연 : " + data.movieInfoResult.movieInfo.actors[0].peopleNm+", " + data.movieInfoResult.movieInfo.actors[1].peopleNm+", " + data.movieInfoResult.movieInfo.actors[2].peopleNm;
    time.innerText = "상영시간 : " + data.movieInfoResult.movieInfo.showTm;
    /*
    fetch(url)
    .then((response) => response.json())
    .then((data) => {


        console.log(data);
        title.innerText = data.movieNm;
    });
    */
}

getData();