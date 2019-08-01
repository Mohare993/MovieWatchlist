const alertDiv = document.getElementById("alerts");

function makeRequest(method, url, body) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.onload = () => {
            if (xhr.status >= 200 && xhr.status <= 299) {
                resolve(xhr.responseText);
            } else {
                reject('Error!: ' + xhr.responseText);
            }
        };
        xhr.open(method, url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(body || null);
    });
}


const userData = {};


function handleSubmit(form) {

    console.log("submitted");

    for (let element of form.elements) {
        if (element.id) {
            userData[element.id] = element.value;
        }
    }

    const dataString = JSON.stringify(userData);
   

    makeRequest('POST', local + 'MovieWatchlist/api/account/createAccount', dataString)
        .then((value) => {

            const dataString2 = (JSON.parse(value));
            sessionStorage.setItem('accId', dataString2.accountId)
            window.location = "main.html";
            console.log(value);
        }).catch((error) => {
            console.warn(error);
            const aDiv = document.createElement("div");
            aDiv.className = "alert alert-danger alert-dismissible fade show";
            aDiv.role = "alert";
            aDiv.innerText = "Username is already taken! Please enter a different one.";
            alertDiv.append(aDiv);
            
        });

    return false;
}


function handleLogin(form) {

    const loginData = {};

    console.log("submitted");

    for (let element of form.elements) {
        if (element.id) {
            loginData[element.id] = element.value;
        }
    }

    const dataString = JSON.stringify(loginData);

    makeRequest('POST', local + 'MovieWatchlist/api/account/login', dataString)
        .then((value) => {
            const dataString2 = (JSON.parse(value));
            sessionStorage.setItem('accId', dataString2.accountId)
            window.location = "main.html";
            console.log(value);
        }).catch((error) => {
            console.warn(error);
            alertDiv.innerHTML = "";
            const aDiv = document.createElement("div");
            aDiv.className = "alert alert-danger alert-dismissible fade show";
            aDiv.role = "alert";
            aDiv.innerText = "Please enter a valid Username and Password.";
            alertDiv.append(aDiv);
        });

    return false;
}

