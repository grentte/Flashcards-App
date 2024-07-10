const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('./database.sqlite', (err) => {
  if (err) {
    console.error(err.message);
  } else {
    console.log('Connected to DataBase.');
  }
});

db.serialize(() => {
  db.all('SELECT * FROM decks', [], (err, rows) => {
    if (err) {
      throw err;
    }
    rows.forEach((row) => {
      console.log(row);
    });
  });
});

db.close((err) => {
  if (err) {
    console.error(err.message);
  } else {
    console.log('Close the database connection.');
  }
});