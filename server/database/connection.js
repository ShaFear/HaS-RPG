var mysql = require("mysql");
var credentials = require("../credentials")


connection =  mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: credentials.databasePassword,
    database: 'development',
});
connection.connect();
