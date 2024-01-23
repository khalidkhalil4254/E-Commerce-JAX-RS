
var userEmail = sessionStorage.getItem("email");

document.getElementById('username').innerText = "User Account: " + userEmail;


// Make a request to the API endpoint to get the list of products
axios.get('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/products/all')
    .then((response) => {
        const products = response.data;
        renderProducts(products);
    })
    .catch((error) => {
        console.error('Error fetching products:', error);
    });

// Function to render product cards dynamically
function renderProducts(products) {
    const productContainer = document.getElementById('productContainer');

    let currentRow; // To keep track of the current row
    products.forEach((product, index) => {
        if (index % 3 === 0) {
            // Start a new row for every third product
            currentRow = document.createElement('div');
            currentRow.classList.add('row');
            productContainer.appendChild(currentRow);
        }

        const colDiv = document.createElement('div');
        colDiv.classList.add('col-md-4', 'mb-4');

        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');

        const img = document.createElement('img');
        img.src = product.imageAddr;
        img.classList.add('card-img-top');
        img.alt = `Product ${index + 1}`;

        const cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        const title = document.createElement('h5');
        title.classList.add('card-title');
        title.textContent = product.title;

        const description = document.createElement('p');
        description.classList.add('card-text');
        description.textContent = product.description;

        const price = document.createElement('p');
        price.classList.add('card-text');
        price.textContent = `Price: $${parseFloat(product.price).toFixed(2)}`;

        const addToCartBtn = document.createElement('button');
        addToCartBtn.classList.add('btn', 'btn-primary');
        addToCartBtn.textContent = 'Add to Cart';
        //---------------------------------------handling each product add to cart button---------------------------------------
        addToCartBtn.addEventListener('click', ()=>{
            // Retrieving a session storage item
            axios.post('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/add', {
        title: product.title,
        email: userEmail
        })
        .then((response) => {
        // Handle the response
            document.getElementById('username').textContent = response.data==1?"Product added successfully!":"products already exists!";
            setTimeout(()=>{
                document.getElementById('username').innerText = "User Account: " + userEmail;
            },5000)
        })
        .catch((error) => {
        // Handle errors
            console.error('Error making POST request:', error);
        });
            //-------------------------------------------------------------------------------------------------------------------
        });

        // Append elements to the card
        cardBody.appendChild(title);
        cardBody.appendChild(description);
        cardBody.appendChild(price);
        cardBody.appendChild(addToCartBtn);

        cardDiv.appendChild(img);
        cardDiv.appendChild(cardBody);

        colDiv.appendChild(cardDiv);
        currentRow.appendChild(colDiv);
    });
}