const express = require('express');
const bodyParser = require('body-parser');
const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static('public'));
app.set('view engine', 'ejs');

// Simulated Database
let products = [];

// Admin credentials
const adminCredentials = { username: 'admin', password: 'admin123' };

// Routes
app.get('/', (req, res) => res.render('index'));

app.get('/login', (req, res) => res.render('login', { role: req.query.role }));

app.post('/login', (req, res) => {
  const { role, username, password } = req.body;
  if (role === 'admin' && username === adminCredentials.username && password === adminCredentials.password) {
    return res.redirect('/admin-dashboard');
  }
  if (role === 'user') {
    return res.redirect('/user-dashboard');
  }
  res.send('Invalid Credentials');
});

app.get('/admin-dashboard', (req, res) => res.render('admin_dashboard', { products }));

app.post('/admin-dashboard', (req, res) => {
  const { name, id, price, category, manufacturingDate, expDate } = req.body;
  products.push({ name, id, price, category, manufacturingDate, expDate });
  res.redirect('/admin-dashboard');
});

app.get('/user-dashboard', (req, res) => {
  res.render('user_dashboard', { products, searchResults: [] });
});

app.post('/user-dashboard', (req, res) => {
  const { searchKey, searchType } = req.body;
  const searchResults = products.filter(product =>
    product[searchType].toLowerCase().includes(searchKey.toLowerCase())
  );
  res.render('user_dashboard', { products, searchResults });
});
// Handle Product Search by User
// Handle Product Search by User
/*app.post('/user-dashboard', (req, res) => {
    const { searchKey, searchType } = req.body;
  
    // Filter products based on the search key and type (name or category)
    const filteredProducts = products.filter((product) =>
      product[searchType].toLowerCase().includes(searchKey.toLowerCase())
    );
  
    // Render the user dashboard with search results
    res.render('user-dashboard', { products: filteredProducts });
  });
  
  */
// User Dashboard (GET route)
  
// Start Server
const PORT = 3000;
app.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));
