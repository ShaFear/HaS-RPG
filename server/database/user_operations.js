require('./connection.js');

signIn = function (login, passwd, res) {
    query = 'SELECT UserID FROM users WHERE login=? and passwd=?';
    values = [login, passwd];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            try {
                res.cookie('UserID', rows[0]['UserID'], {signed: true});
                res.status(200).send('Login and password are correct');
                return;
            }
            catch (e) {
                res.status(258).send('Wrong password or username');
                return;
            }
        }
        if (err) {
            res.status(260).send('Database error');
            console.log(err);
            return;
        }
    });
}

addUser = function (login, passwd, res) {
    query = "SELECT add_user(?)"
    values = [[login, passwd]];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send('User added');
        } else if (err.code == 'ER_DUP_ENTRY')
            res.status(259).send('User arleady exists');
        else {
            res.status(260).send('Database error');
            console.log(err);
        }
    });
}

getCharacters = function (user_id, res) {
    query = "select * from characters c join users u on u.login=(select login from users where UserID=?) where c.users_login=(select login from users where UserID=?)"
    values = [user_id, user_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            for (j = 0; j < rows.length; j++)
                rows[j]['passwd'] = null
            res.status(200).send(JSON.stringify(rows));
        } else {
            res.status(260).send('Database error');
            console.log(err);
        }
    });
}

setChaseID = function (user_id, chase_id, res) {
    query = "UPDATE users SET ChaseID=? WHERE UserID=?;"
    values = [chase_id, user_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send('Changed chaseID for user');
        }
        else {
            res.status(260).send('Database error');
            console.log(err);
        }
    });
}

setHunterID = function (user_id, hunter_id, res) {
    query = "UPDATE users SET HunterID=? WHERE UserID=?;"
    values = [hunter_id, user_id];
    connection.query(query, values, function (err, rows, fields) {
        if (!err) {
            res.status(200).send('Changed HunterID for user');
        }
        else {
            res.status(260).send('Database error');
            console.log(err);
        }
    });
}
