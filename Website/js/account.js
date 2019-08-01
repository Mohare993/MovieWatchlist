const alertDiv = document.getElementById("alerts");
const accId = sessionStorage.getItem('accId');

function deleteAcc() {


    makeRequest('DELETE', cloud + 'MovieWatchlist/api/account/delete/' + accId)
        .then((value) => {
            sessionStorage.clear();
            alert("Account Deleted");
            window.location = "splash.html";
            console.log(value, "DELETED");
        }).catch((error) => {
            console.warn(error);
        });

    return false;
}

function handleUpdateSubmit(form) {

    const userData = {};

    console.log("submitted");

    for (let element of form.elements) {
        if (element.id) {
            userData[element.id] = element.value;
        }
    }

    const dataString = JSON.stringify(userData);
   

    makeRequest('POST', cloud + 'MovieWatchlist/api/account/update/' + accId, dataString)
        .then((value) => {
            alert("Account Updated");
            alertDiv.innerHTML = "";
            console.log(value);
        }).catch((error) => {
            console.warn(error);
            alertDiv.innerHTML = "";
            const aDiv = document.createElement("div");
            aDiv.className = "alert alert-danger alert-dismissible fade show";
            aDiv.role = "alert";
            aDiv.innerText = "Username is already taken! Please enter a different one.";
            alertDiv.append(aDiv);
        });

    return false;
}

function load_data() {


    const inUsername = document.getElementById("username"); 
    const inUserFullname = document.getElementById("fullName");  
    const inUserEmail = document.getElementById("email");  
    const inUserPass = document.getElementById("password");    
    
    makeRequest('GET', cloud + 'MovieWatchlist/api/account/get/' + accId)
    .then((value) => {
        
    const ddd = JSON.parse(value);

    inUsername.value = ddd.username;
    inUserFullname.value = ddd.fullName;
    inUserEmail.value = ddd.email;
    inUserPass.value =ddd.password;
    }).catch((error) => {
        console.warn(error);
    });

    return false;
  

}

load_data();