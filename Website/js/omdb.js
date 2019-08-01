const BASE_URL = "http://www.omdbapi.com/?apikey=a172e1b0";
const listId = sessionStorage.getItem('listID');
const moviesDiv = document.getElementById("moivesfill");



function searchSubmit(form) {
    const xhr = new XMLHttpRequest();
    xhr.onload = () => {
        const data = JSON.parse(xhr.responseText);
        console.log('Search  request loaded. Data:', data)
        displayResults(data);
    };
    xhr.open("GET", BASE_URL + "&s=" + form.searchInput.value);
    xhr.send();

}

function handleMovieClicked(movie) {
    console.log("movie clicked", movie);
    sessionStorage.setItem('movieData', movie.imdbID);

    const imbdID = sessionStorage.getItem('movieData');

    makeRequest("GET", BASE_URL + "&i=" + imbdID)
        .then((value) => {
            handleMovieClicked1(value);
        }).catch((error) => {
            console.warn(error);
        });



    return false;
}

function handleMovieClicked1(movie) {

    makeRequest('POST', cloud + 'MovieWatchlist/api/movie/createMovie/' + listId, movie)
        .then((value) => {
            window.location = "movietable.html";
        }).catch((error) => {
            console.warn(error);
        });


    return false;

}



function displayResults(data) {

    clearSearchResults();
    const moviesDiv = document.getElementById("searchResults");
    for (let movieData of data.Search) {

        console.log(movieData);

        const mDiv = document.createElement("div");
        mDiv.className = 'dropdown-item  align-items-center';
        mDiv.setAttribute("name", "movie");

        mDiv.addEventListener("click", () => handleMovieClicked(movieData));

        let title = document.createElement("h5");
        title.innerText = movieData.Title + " (" + movieData.Year + ")";


        mDiv.append(title);

        moviesDiv.append(mDiv);



    }

    moviesDiv.style.display = "block";
}



function getmovies() {

    makeRequest('GET', cloud + 'MovieWatchlist/api/movie/get/' + listId)
    .then((value) => {
        var data = JSON.parse(value)
        displayMovies(data);
        console.log(data);
    }).catch((error) => {
        console.warn(error);
    });

return false;

}

function displayMovies(data) {
   
    for (let movieData of data) {

        const mDiv = document.createElement("div");
        mDiv.className = "card m-4 p-2";
        mDiv.style = "max-width: 540px;";

        createElement('h5', movieData.Title , 'card-header', mDiv);
       

        const img = document.createElement('img');
        img.className = "img-fluid rounded float-left";
        img.setAttribute('src', movieData.Poster);

        mDiv.append(img);

        createElement('h5', "Year : " + movieData.Year, 'text', mDiv);
        createElement('h6', "Genre : " + movieData.Genre , 'text', mDiv);
        createElement('p', "Plot : " + movieData.Plot , 'text', mDiv);
        createElement('h3', "imdbRating : " + movieData.imdbRating, 'text', mDiv);
     
        moviesDiv.append(mDiv);

        createButton('Delete', (e) => deleteMovie(movieData), mDiv);

    }
}

function createElement(tag, text, className, parent){
    const e = document.createElement(tag);
    e.innerText = text;
    e.className = className;
    if(parent){
        parent.append(e);
    }
    return e;
}

function createButton(text, func, parent){
    const btn = document.createElement('input');
    btn.setAttribute('value', text);
    btn.setAttribute('type', 'button');
    btn.setAttribute('class', 'btn1 btn-danger');
    btn.addEventListener('click', func);
    parent.append(btn);
}

function deleteMovie(movie) {

    sessionStorage.setItem('movieID', movie.id);
    const movieID = sessionStorage.getItem('movieID');

    makeRequest('DELETE', cloud + 'MovieWatchlist/api/movie/delete/' + movieID)
    .then((value) => {
        alert("Movie Deleted");
        window.location = "movietable.html";
        console.log(value, "DELETED");
    }).catch((error) => {
        console.warn(error);
    });

return false;
}

getmovies();


function clearSearchResults() {
    const resultsDiv = document.getElementById('searchResults');
    while (resultsDiv.lastChild) {
        resultsDiv.removeChild(resultsDiv.lastChild);
    }
}

