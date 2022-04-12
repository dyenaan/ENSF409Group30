package edu.ucalgary.ensf409;

public enum ClientTypes {
    MALE {
        public int clientID() {
            return 1;
        }
    }, FEMALE {
        public int clientID() {
            return 2;
        }
    }, CHILDOE {
        public int clientID() {
            return 3;
        }
    }, CHILDUE {
        public int clientID() {
            return 4;
        }
    };
    public abstract int clientID();
}