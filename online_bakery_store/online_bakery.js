// BakeryItem Constructor
function BakeryItem(name, price, weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }
  
  // Add Bakery Items
  const bakeryItems = [
    new BakeryItem("Chocolate Cake", 250, "1/2kg"),
    new BakeryItem("Chocolate Cake", 500, "1kg"),
    new BakeryItem("Chocolate Cake", 1000, "2kg"),
    new BakeryItem("Croissant", 50, "100g"),
    new BakeryItem("Croissant", 100, "200g"),
    new BakeryItem("Croissant", 150, "250g"),
    new BakeryItem("Red Velvet Pastry", 150, "200g"),
    new BakeryItem("Red Velvet Pastry", 200, "400g"),
    new BakeryItem("Red Velvet Pastry", 250, "600g"),
  ];
  
  // Cart Object
  const cart = {
    items: [],
    addItem(item) {
      this.items.push(item);
      renderCart();
    },
    getTotal() {
      return this.items.reduce((total, item) => total + item.price, 0);
    },
  };
  
  // Render Bakery Items
  function renderItems() {
    const itemList = document.getElementById("item-list");
    bakeryItems.forEach((item, index) => {
      const div = document.createElement("div");
      div.innerHTML = `
        <span>${item.name} - ₹${item.price} (${item.weight})</span><br>
        <button onclick="addToCart(${index})">Add to Cart</button>
      `;
      itemList.appendChild(div);
    });
  }
  
  // Add Item to Cart
  function addToCart(index) {
    cart.addItem(bakeryItems[index]);
  }
  
  // Render Cart
  function renderCart() {
    const cartItems = document.getElementById("cart-items");
    const total = document.getElementById("total");
    
    cartItems.innerHTML = "";
    cart.items.forEach((item) => {
      const div = document.createElement("div");
      div.textContent = `${item.name} - ₹${item.price}`;
      cartItems.appendChild(div);
    });
    
    total.textContent = `Total: ₹${cart.getTotal()}`;
  }
  
  // Initialize App
  document.addEventListener("DOMContentLoaded", () => {
    renderItems();
  });
  