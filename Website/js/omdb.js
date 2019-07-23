const BASE_URL = "http://www.omdbapi.com/?apikey=a172e1b0";


// const searchResults = document.getElementById("movieResult");
// const newLine = document.createElement('p');
// let x = "";


function searchSubmit(form) {

    

    const xhr = new XMLHttpRequest();

    xhr.onload = () => {
        const data = JSON.parse(xhr.responseText);
        console.log('Search  request loaded. Data:', data)
        displayResults(data);
        // populatePage(data);
        // displayResult(data);

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

    
    const moviesDiv = document.getElementById("moivesfill");
    moviesDiv.innerHTML = "";
   

    for (let movieData of data.Search) {

        const mDiv = document.createElement("div");
        mDiv.setAttribute("name", "movie");
        mDiv.className = "card m-4 p-2";
        mDiv.addEventListener("click", () => handleMovieClicked(movieData));

        var myImage = new Image(100, 150);
        myImage.src = movieData.Poster;
        



        let title = document.createElement("h4");
        title.innerText = movieData.Title;

        let year = document.createElement("h6");
        year.innerText = movieData.Year;

        mDiv.append(myImage);


        mDiv.append(title);
        mDiv.append(year);

        moviesDiv.append(mDiv);


    }
}





// function populatePage({Search, totalResults, ...everythingElse }) {
//     // Container refs
//     const searchResults = document.getElementById("movieResult");
//     const movies = document.getElementById("totalResult");


//     // Generate meovies
//     for (let movie of Search) {
//        searchResults.append(getMovies(movie));
//     }
// }


// function getMovies(movie) {
//     const wrapper = document.createElement('div');
//     wrapper.className = 'card m-3';

//     const title = document.createElement('div');
//     title.className = 'card-body';
//     title.innerText = movie.Title;
//     wrapper.append(title);

//     for(let key in movie) {
//         wrapper.append(getKeyValueParagraph(key, movie[key]));
//     }

//     return wrapper;
// }

// function getKeyValueParagraph(key, value) {
//     const el = document.createElement('p');
//     el.innerHTML = '<b>' + key + '</b>: ' + value;
//     return el;
// }




// function displayResult(data) {


//     for (let i in data.Search) {

//             x += "<p>" + data.Search[i].Title + "</p>";
//             x += "<p>" + data.Search[i].Year + "</p>";
//             x += "<p>" + data.Search[i].imdbID + "</p>";
//             x += "<p>" + data.Search[i].Type + "</p>";


//      }


//     document.getElementById("movieResult").innerHTML = x;

// }
