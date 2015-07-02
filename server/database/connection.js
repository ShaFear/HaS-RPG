var mysql = require("mysql");

connection =  mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'development',
});
connection.connect();
