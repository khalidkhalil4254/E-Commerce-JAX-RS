function submitForm() {
    // Get form data
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const addr = document.getElementById('addr').value;
    const phone = document.getElementById('phone').value;

    // Validate form data (add your own validation logic here)

    // Create data object for Axios POST request
    const postData = {
        firstname: firstname,
        lastname: lastname,
        email: email,
        password: password,
        addr: addr,
        phone: phone
    };

    // Make a POST request using Axios
    axios.post('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/auth/signUp', postData)
        .then((response) => {
            // Handle successful response
            console.log('Sign-up successful:', response.data);
            
            
            axios.post('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/auth/signIn', 
                {email:email,  password:password}
            ).then((response) => {
            console.log("Username: " + email + ", Password: " + password);
            console.log(response.data);

            if(response.data === "{Status: 1}"){
                // Setting a session storage item
                sessionStorage.setItem("email", email);
                window.location.href="http://127.0.0.1:5500/storeProducts.html";
            }

        })
        .catch((err) => {
            console.error("Error: " + err);
        });

        })
        .catch((error) => {
            // Handle error
            console.error('Error in sign-up:', error);
        });
}