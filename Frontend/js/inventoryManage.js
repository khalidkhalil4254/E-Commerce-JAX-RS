axios.get('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/products/all').then(res => {

(res.data).forEach(element => {
  const productList = document.getElementById('productList');
  const listItem = document.createElement('li');
  listItem.className = 'list-group-item';

  // Display specific fields from the product
  listItem.innerHTML = `
  <img src="${element.imageAddr}" alt="${element.title}" style="max-width: 100px; max-height: 100px;">
  <div>
    <h5>Title: ${element.title}</h5>
    <br>
    <p>Description: <input class="desInput" style="width: 720px; height: 40px;" type="text" value="${element.description}"></p>
    <label>Price ($): <input style="margin-left:25px;" type="number" class="priceInput" name="positiveNumber" min="1" pattern="[0-9]*" value="${element.price}" required></label>
    <br><br>
    <label>Quantity(U): <input type="number" class="qntInput" name="positiveNumber" min="1" pattern="[0-9]*" value="${element.qnt}" required></label>
    <br><br>
    <button class='edit_btn'>Edit</button>
    <button class='delete_btn'>Delete</button>
  </div>
`;





  var EditBtn = listItem.getElementsByClassName("edit_btn")[0];
  var DeleteBtn = listItem.getElementsByClassName('delete_btn')[0];


  EditBtn.addEventListener('click', () => {
    console.log("Editing: " + element.title);

    axios.put('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/products/edit',
      {
        title: element.title,
        des: listItem.getElementsByClassName('desInput')[0].value,
        price: parseFloat(listItem.getElementsByClassName('priceInput')[0].value),
        qnt: parseInt(listItem.getElementsByClassName('qntInput')[0].value)
      }
    ).then((item) => {
      window.location.reload();
      }).catch((err) => {

      });
  });




  DeleteBtn.addEventListener('click', () => {
    console.log("Deleting: " + element.title);

    axios.delete('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/products/delete', {
      headers: {
        'Content-Type': 'application/json',
      },
      data: { title: element.title }
    })
      .then((res) => {
        window.location.reload();
        console.log(res);
      })
      .catch((err) => {
        console.log("Error deleting product: " + err);
      });

  });




  productList.appendChild(listItem);
});


}).catch(err => {
console.log("Error getting all products: " + err);
});



function addProduct() {
const title = document.getElementById('productName').value;
const description = document.getElementById('description').value;
const imageAddr = document.getElementById('imageAddr').value;
const price = document.getElementById('price').value;
const quantity = document.getElementById('quantity').value;

// Validate input
if (!title || !description || !imageAddr || !price || !quantity) {
  alert('Please fill in all fields');
  return;
}

// Create product object
const product = {
  title: title,
  description: description,
  imageAddr: imageAddr,
  price: parseFloat(price),
  quantity: parseInt(quantity),
};

// Make Axios POST request
axios.post('http://localhost:8080/demo3-1.0-SNAPSHOT/apiV1/products/add', product)
  .then(response => {
    // Handle success
    console.log(response.data);
    displayProducts(response.data);
  })
  .catch(error => {
    // Handle error
    console.log('Error adding product: ', error);
  });

// Clear input fields
document.getElementById('productName').value = '';
document.getElementById('description').value = '';
document.getElementById('imageAddr').value = '';
document.getElementById('price').value = '';
document.getElementById('quantity').value = '';
}

function displayProducts(products) {
const productList = document.getElementById('productList');
const listItem = document.createElement('li');
listItem.className = 'list-group-item';

// Display specific fields from the product
listItem.innerHTML = `
  <img src="${products.imageAddr}" alt="${products.title}" style="max-width: 100px; max-height: 100px;">
  <div>
    <h5>${products.title}</h5>
    <p>${products.description}</p>
    <p>Price: $${products.price}</p>
    <p>Quantity: ${products.quantity}</p>
  </div>
`;

productList.appendChild(listItem);
}
