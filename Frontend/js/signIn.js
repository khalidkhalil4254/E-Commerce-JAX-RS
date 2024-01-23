function goToSignUp(){
    window.location.href="http://127.0.0.1:5500/signUp.html";
}

    function submitForm() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const postReqData = {
            email: username,
            password: password
        };

        axios.post('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/auth/signIn', postReqData)
            .then((response) => {
                console.log("Username: " + postReqData.username + ", Password: " + postReqData.password);
                console.log(response.data);


                if(response.data === "{Status: 1}"){
                    // Setting a session storage item
                    sessionStorage.setItem("email", username);
                    window.location.href="http://127.0.0.1:5500/storeProducts.html";
                }

            })
            .catch((err) => {
                console.error("Error: " + err);
            });
    }