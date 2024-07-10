const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('./database.sqlite', (err) => {
  if (err) {
    console.error(err.message);
  } else {
    console.log('Connected to DataBase.');
  }
});

db.serialize(() => {
  db.run('DELETE FROM decks', (err) => {
    if (err) {
      console.error('Error clearing decks table:', err.message);
    } else {
      console.log('Decks table cleared.');
    }
  });
});

db.close((err) => {
  if (err) {
    console.error(err.message);
  } else {
    console.log('Closed the database connection.');
  }
});