    package org.example.flashcardsapp.database;

    public class Session {
        private static Session instance;
        private User currentUser;

        private Session() {}

        public static synchronized Session getInstance() {
            if (instance == null) {
                instance = new Session();
            }
            return instance;
        }

        public User getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(User user) {
            this.currentUser = user;
        }
    }
