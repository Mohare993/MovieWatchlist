const listsDiv = document.getElementById('lists');
const accId = sessionStorage.getItem('accId');
function displayLists() {

    makeRequest('GET', 'http://localhost:8080/MovieWatchlist/api/lists/get/' + accId)
        .then(value => displayLists1(JSON.parse(value)));
}

function displayLists1(value) {

    for (let list of value) {
        const oDiv = document.createElement('div');
        const testDiv =  document.createElement('div');
        createElement('h4',  "Listname : ", 'card-header', oDiv);
        

    
      

        oDiv.className = 'card m-4 p-2';
        // oDiv.addEventListener('click', () => handleListClicked(list));


        let lName = document.createElement('h4');
        lName.innerText = list.listName;

        oDiv.append(lName);

        listsDiv.append(oDiv);

        createButton('Modify', (e) => handleListClicked(list), oDiv);
        createButton('Delete', (e) => console.log('delete btn click'), oDiv);

    }
   
}

function handleListClicked(list) {
    console.log('List CLICKED!', list.listId);
    sessionStorage.setItem('listID', list.listId);
    window.location = "movietable.html";
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
            window.location = "main.html";
        }).catch((error) => {
            console.warn(error);
        });

    return false;
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
    btn.addEventListener('click', func);
    parent.append(btn);
}

displayLists();
