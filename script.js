
  // Function to handle registration
function registerUser(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const age = parseInt(document.getElementById('age').value, 10);
    const email = document.getElementById('email').value;
    const categories = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(
        checkbox => checkbox.value
    );

    if (age < 10 || age > 80) {
        alert('Age must be between 10 and 80 years.');
        return;
    }

    // Get existing users from localStorage
    const registeredUsers = JSON.parse(localStorage.getItem('registeredUsers')) || {};

    // Save new user data
    registeredUsers[email] = { name, age, categories };
    localStorage.setItem('registeredUsers', JSON.stringify(registeredUsers));

    alert('Registration successful! Please log in.');
    window.location.href = 'index.html'; // Redirect to login page
}

// Function to handle login
function loginUser(event) {
    event.preventDefault();

    const email = document.getElementById('loginEmail').value;

    // Get users from localStorage
    const registeredUsers = JSON.parse(localStorage.getItem('registeredUsers')) || {};

    if (registeredUsers[email]) {
        // Save the logged-in user to localStorage
        localStorage.setItem('loggedInUser', JSON.stringify(registeredUsers[email]));
        window.location.href = 'dashboard.html'; // Redirect to dashboard
    } else {
        alert('User not found. Please register.');
    }
}

// Function to display books on the dashboard
function displayBooks() {
    const user = JSON.parse(localStorage.getItem('loggedInUser'));

    if (user) {
        const bookCategories = document.getElementById('bookCategories');
        user.categories.forEach(category => {
            const li = document.createElement('li');
            li.classList.add('list-group-item');
            li.textContent = category;
            bookCategories.appendChild(li);
        });
    } else {
        alert('No user data found. Please log in.');
        window.location.href = 'index.html'; // Redirect to login page
    }
}

// Attach event listeners when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('registrationForm')) {
        document.getElementById('registrationForm').addEventListener('submit', registerUser);
    }

    if (document.getElementById('loginForm')) {
        document.getElementById('loginForm').addEventListener('submit', loginUser);
    }

    if (window.location.pathname.includes('dashboard.html')) {
        displayBooks();
    }
});
