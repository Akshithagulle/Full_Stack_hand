const http = require('http');
const fs = require('fs');
const url = require('url');
const querystring = require('querystring');

// Static credentials
const validUser = { username: 'akshitha', password: 'Akshitha960' };

const server = http.createServer((req, res) => {
    const parsedUrl = url.parse(req.url, true);
    const pathname = parsedUrl.pathname;

    if (req.method === 'GET') {
        if (pathname === '/' || pathname === '/login.html') {
            // Serve the login page
            fs.readFile('./login.html', (err, content) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/html' });
                    res.end('<h1>Internal Server Error</h1>');
                } else {
                    res.writeHead(200, { 'Content-Type': 'text/html' });
                    res.end(content, 'utf-8');
                }
            });
        } else if (pathname === '/index.html') {
            // Serve the welcome page
            fs.readFile('./index.html', (err, content) => {
                if (err) {
                    res.writeHead(500, { 'Content-Type': 'text/html' });
                    res.end('<h1>Internal Server Error</h1>');
                } else {
                    res.writeHead(200, { 'Content-Type': 'text/html' });
                    res.end(content, 'utf-8');
                }
            });
        } else {
            // Serve 404 page
            fs.readFile('./404.html', (err, content) => {
                res.writeHead(404, { 'Content-Type': 'text/html' });
                res.end(content || '<h1>Page Not Found</h1>', 'utf-8');
            });
        }
    } else if (req.method === 'POST' && pathname === '/login') {
        // Handle login
        let body = '';
        req.on('data', chunk => {
            body += chunk.toString();
        });

        req.on('end', () => {
            const credentials = querystring.parse(body);

            // Validate the username and password
            if (
                credentials.username === validUser.username &&
                credentials.password === validUser.password
            ) {
                // Redirect to the welcome page
                res.writeHead(302, { Location: '/index.html' });
                res.end();
            } else {
                // Redirect back to login with an error
                res.writeHead(302, { Location: '/login.html?error=1' });
                res.end();
            }
        });
    } else {
        // Unknown route - serve 404 page
        fs.readFile('./404.html', (err, content) => {
            res.writeHead(404, { 'Content-Type': 'text/html' });
            res.end(content || '<h1>Page Not Found</h1>', 'utf-8');
        });
    }
});

// Start the server
server.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});
