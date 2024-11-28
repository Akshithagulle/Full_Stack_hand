const express = require('express');
const app = express();
const port = 3000;
const bodyParser = require('body-parser');

// Middleware to parse JSON and URL-encoded data
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// In-memory database for products
let products = [
  { id: 1, name: 'Headset', description: 'Features a noise-canceling mic, ANC, great audio quality', price: 5500, availability: true },
  { id: 2, name: 'Laptop', description: 'Gaming laptop', price: 55000, availability: true },
  { id: 3, name: 'Smartphone', description: 'Flagship smartphone', price: 10800, availability: true },
];

app.use(express.static('public'));

// Route to display list of products
app.get('/products', (req, res) => {
    res.send(`
      <!DOCTYPE html>
      <html lang="en">
      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Catalog</title>
        <style>
        body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: pink;
  color: #333;
}

header {
  background-color:  green;
  color: white;
  padding: 10px 20px;
  text-align: center;
}

h1, h2 {
  color:pink ;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  background: azure;
  margin: 10px 0;
  padding: 15px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

form {
  background: #fff;
  padding: 20px;
  margin: 20px 0;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin: 10px 0 5px;
  font-weight: bold;
}

input, textarea, select, button {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

button {
  background-color: blue;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}

        </style>
      </head>
      <body>
        <header>
          <h1>Product Catalog</h1>
        </header>
        <main>
          <h2>Available Products</h2>
          <ul>
            ${products
              .map(
                (product) => `
                <li>
                  <strong>${product.name}</strong>: ${product.description}<br>
                  Price: ₹${product.price}<br>
                  Availability: ${product.availability ? 'In Stock' : 'Out of Stock'}
                </li>
              `
              )
              .join('')}
          </ul>
          <h2>Add a New Product</h2>
          <form action="/add-product" method="POST">
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" required><br>
  
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea><br>
  
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required><br>
  
            <label for="availability">Availability:</label>
            <select id="availability" name="availability">
              <option value="true">In Stock</option>
              <option value="false">Out of Stock</option>
            </select><br>
  
            <button type="submit">Add Product</button>
          </form>
        </main>
      </body>
      </html>
    `);
  });
  

// Route to add a new product
app.post('/add-product', (req, res) => {
  const { name, description, price, availability } = req.body;
  const newProduct = {
    id: products.length + 1,
    name,
    description,
    price: parseFloat(price),
    availability: availability === 'true',
  };
  products.push(newProduct);
  res.redirect('/products');
});

// Start server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
