const listId = sessionStorage.getItem('listID');
const moviesDiv = document.getElementById("moivesfill");

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

        createElement('h5', movieData.Year , 'text', mDiv);
      
        moviesDiv.append(mDiv);

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

function deleteMovie(movie){

    sessionStorage.setItem('movieID', movie.movieId);
    const movieID = sessionStorage.getItem('movieID');

    makeRequest('DELETE', cloud + 'MovieWatchlist/api/lists/delete/' + movieID)
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