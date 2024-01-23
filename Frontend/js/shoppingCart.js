        // Fetch products from the API
        axios.get(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/items?email=${sessionStorage.getItem("email")}`)
            .then(response => {
                const products = response.data;
                displayProducts(products);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });

        function displayProducts(products) {
            const cartItemsContainer = document.getElementById('cart-items');

            products.forEach(product => {
                const card = document.createElement('div');
                card.className = 'col-md-4 mb-4';

                card.innerHTML = `
        <div class="card">
          <img src="${product.imageAddr}" class="card-img-top" alt="${product.title}">
          <div class="card-body">
            <h5 class="card-title">${product.title}</h5>
            <p class="card-text">Price: $${product.price.toFixed(2)}</p>
            <p class="card-text">Quantity: ${product.quantity}</p>
            <p class="card-text">Total: $${(product.amount).toFixed(2)}</p>
            <button class="btn btn-success" onclick="increaseQuantity('${product.title}','${product.quantity}')">+</button>
            <button class="btn btn-warning" onclick="decreaseQuantity('${product.title}','${product.quantity}')">-</button>
          </div>
          <button class="btn btn-danger remove-button" onclick="removeProduct('${product.title}')">Remove</button>
        </div>
      `;

                cartItemsContainer.appendChild(card);
            });
        }

        function increaseQuantity(title, qnt) {
            if (qnt < 3) {
                // Handle increasing the quantity of the product
                // You can implement this part based on your requirements
                axios.put(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/edit/${sessionStorage.getItem("email")}/${title}/${++qnt}`).then((response)=>{
                    console.log(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/edit/${sessionStorage.getItem("email")}/${title}/${++qnt}`);
                    window.location.reload(); // Reload the page
                })
                .catch((error)=>{
                    console.error('Error making PUT request:', error);
                });
            }
        }

        function decreaseQuantity(title, qnt) {
            if (qnt > 1) {
                // Handle increasing the quantity of the product
                // You can implement this part based on your requirements
                axios.put(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/edit/${sessionStorage.getItem("email")}/${title}/${--qnt}`).then((response)=>{
                    console.log(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/edit/${sessionStorage.getItem("email")}/${title}/${--qnt}`);
                    window.location.reload(); // Reload the page
                })
                .catch((error)=>{
                    console.error('Error making PUT request:', error);
                });

            }
        }


        function removeProduct(title) {
            // Send a DELETE request
            axios.delete(`http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/shopping-cart/remove/${sessionStorage.getItem("email")}/${title}`)
                .then((response) => {
                    console.log('DELETE request successful:', response.data);
                    window.location.reload(); // Reload the page
                })
                .catch((error) => {
                    console.error('Error making DELETE request:', error);
                });
        }