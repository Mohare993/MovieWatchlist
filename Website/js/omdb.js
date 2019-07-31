const BASE_URL = "http://www.omdbapi.com/?apikey=a172e1b0";
const listId = sessionStorage.getItem('listID');



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
    const dataString =JSON.stringify(movie);
    sessionStorage.setItem('movieData', dataString);
    window.location = "resultPage.html";
}


function displayResults(data) {

    clearSearchResults();

    
    const moviesDiv = document.getElementById("searchResults");
   
    
   

    for (let movieData of data.Search) {

        console.log(movieData);

        const mDiv = document.createElement("div");
        mDiv.className = 'dropdown-item';
        mDiv.setAttribute("name", "movie");
        mDiv.addEventListener("click", () => handleMovieClicked(movieData));     

        let title = document.createElement("h3");
        title.innerText = movieData.Title;

        mDiv.append(title);
        moviesDiv.append(mDiv);

     
       
    }

    moviesDiv.style.display = "block";
}



function getmovies() {

 
    makeRequest('GET', 'http://localhost:8080/MovieWatchlist/api/movie/get/' + listId)
        .then((value) => {

            var $table = $('#table');
            var mydata = JSON.parse(value); 

            $(function () {
                $('#table').bootstrapTable({
                    data: mydata
                });
            });


            console.log(value);
        }).catch((error) => {
            console.warn(error);
        });

    return false;
}

function checkForGames() {
    //check for games matching text
    clearSearchResults();
    const searchStr = document.getElementById('searchText').value.trim();
    if(searchStr === "") {
        hideResults();
        return;
    }

    const resultsDiv = document.getElementById('searchResults');
    makeRequest("GET", BASE_URL + "&s=" + searchStr)
        .then(value => {
            if(value && value.length > 0) {
               
                for(const key in value){
                    createGame(value[key], resultsDiv);
                }
                //display results
                document.getElementById("searchResults").style.display = "block";
            } else {
                console.log("hide");
                hideResults();
            }
        });
}

function createGame(game, parentDiv) {
    const div = document.createElement('div');
    div.className = 'dropdown-item';
    div.addEventListener('click', (e) => {
        sessionStorage.setItem('gameId', game.id);
        window.location = "game.html";
    });

    const h = document.createElement('h3');
    h.innerText = game.Title;
    const p = document.createElement('p');
    p.innerText = game.Year;
    div.append(h);
    div.append(p);
    parentDiv.append(div);
}

function clearSearchResults() {
    const resultsDiv = document.getElementById('searchResults');
    while(resultsDiv.lastChild) {
        resultsDiv.removeChild(resultsDiv.lastChild);
    }
}

function hideResults() {
    const resultDiv = document.getElementById("searchResults");
    if(resultDiv.style.display === "block") {
       resultDiv.style.display = "none";
    }
}

function makeDivElement(tag, text, className, parentDiv) {
    const e = document.createElement(tag);

    if(text) { e.innerText = text; }
    if(className) { e.className = className; }
    if(parentDiv) { parentDiv.append(e); }
    
    return e;
}