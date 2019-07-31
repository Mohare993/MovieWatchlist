const listsDiv = document.getElementById('lists');
const accId = sessionStorage.getItem('accId');
function displayLists() {

    makeRequest('GET', 'http://localhost:8080/MovieWatchlist/api/lists/get/' + accId)
        .then(value => displayLists1(JSON.parse(value)));
}

function displayLists1(value) {

    for (let list of value) {
        const oDiv = document.createElement('div');

        oDiv.className = 'card m-4 p-2';
        oDiv.addEventListener('click', () => handleListClicked(list));

        let lName = document.createElement('h4');
        lName.innerText = list.listName;

        oDiv.append(lName);

        listsDiv.append(oDiv);

    }
}

function handleListClicked(list) {
    console.log('List CLICKED!', list.listId);
    sessionStorage.setItem('listID', list.listId);

    return false;
}

function createList(form) {

    const listData = {};

    console.log("submitted");

    for (let element of form.elements) {
        if (element.id) {
            listData[element.id] = element.value;
        }
    }

    const dataString = JSON.stringify(listData);



    makeRequest('POST', 'http://localhost:8080/MovieWatchlist/api/lists/create/' + accId + "/", dataString)
        .then((value) => {
            window.location = "main%20copy.html";
        }).catch((error) => {
            console.warn(error);
        });

    return false;
}

displayLists();
