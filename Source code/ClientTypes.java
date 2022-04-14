package edu.ucalgary.ensf409;

/*
 * ClientTypes is an enumeration that is used to have a consistent way of getting the ClientIDs of the different clients
 */

public enum ClientTypes {
    MALE {
        public int clientID() {
            return 1;
        }
        public String toString() { return "Male"; }
    }, FEMALE {
        public int clientID() {
            return 2;
        }
        public String toString() { return "Female"; }
    }, CHILDOE {
        public int clientID() {
            return 3;
        }
        public String toString() { return "ChildOE"; }
    }, CHILDUE {
        public int clientID() {
            return 4;
        }
        public String toString() { return "ChildUE"; }

    };
    public abstract int clientID();
    public abstract String toString();
}