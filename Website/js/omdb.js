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
    const dataString = JSON.stringify(movie);
    sessionStorage.setItem('movieData', dataString);

    makeRequest('POST', 'http://localhost:8080/MovieWatchlist/api/movie/createMovie/' + listId, dataString)
        .then((value) => {
            window.location = "movietable.html";
            console.log(value);
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


    makeRequest('GET', 'http://localhost:8080/MovieWatchlist/api/movie/get/' + listId)
        .then((value) => {

            var mydata = JSON.parse(value);

            var $table = $('#table'),
                $alertBtn = $('#alertBtn'),
                full_screen = false;

            $(function () {
                $table.bootstrapTable({
                    toolbar: ".toolbar",
                    showRefresh: true,
                    search: true,
                    showToggle: true,
                    showColumns: true,
                    pagination: true,
                    striped: true,
                    sortable: true,
                    pageSize: 8,
                    pageList: [8, 10, 25, 50, 100],

                    formatShowingRows: function (pageFrom, pageTo, totalRows) {
                        //do nothing here, we don't want to show the text "showing x of y from..."
                    },
                    formatRecordsPerPage: function (pageNumber) {
                        return pageNumber + " rows visible";
                    },
                    icons: {
                        refresh: 'fa fa-refresh',
                        toggle: 'fa fa-th-list',
                        columns: 'fa fa-columns',
                        detailOpen: 'fa fa-plus-circle',
                        detailClose: 'fa fa-minus-circle'
                    },
                    data: mydata
                });
            });

            $(function () {
                $alertBtn.click(function () {
                    alert("You pressed on Alert");
                });
            });


            function operateFormatter(value, row, index) {
                return [
                    '<a rel="tooltip" title="Like" class="table-action like" href="javascript:void(0)" title="Like">',
                    '<i class="fa fa-heart"></i>',
                    '</a>',
                    '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:void(0)" title="Edit">',
                    '<i class="fa fa-edit"></i>',
                    '</a>',
                    '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:void(0)" title="Remove">',
                    '<i class="fa fa-remove"></i>',
                    '</a>'
                ].join('');
            }

            window.operateEvents = {
                'click .like': function (e, value, row, index) {
                    alert('You click like icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);
                },
                'click .edit': function (e, value, row, index) {
                    console.log(value, row, index);
                },
                'click .remove': function (e, value, row, index) {
                    alert('You click remove icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);
                }
            };



            console.log(value);
        }).catch((error) => {
            console.warn(error);
        });

    return false;
}



function clearSearchResults() {
    const resultsDiv = document.getElementById('searchResults');
    while (resultsDiv.lastChild) {
        resultsDiv.removeChild(resultsDiv.lastChild);
    }
}

