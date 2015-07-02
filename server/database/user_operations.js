require('./connection.js');

addUser = function(login, passwd, res){
  query = 'INSERT INTO users (login, passwd) VALUES(?)';
  values = [[login, passwd]];
  connection.query(query, values, function(err, rows, fields) {
    if(!err)
      res.status(200).send('User added');
    else if (err.code == 'ER_DUP_ENTRY')
      res.status(204).send('User arleady exists');
    else {
      res.status(205).send('Database error');
      console.log(err);}});
}
