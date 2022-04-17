<h1> Logistics Program </h1>

<h2> Contents of the README file </h2>

* Introduction
* Installing, compiling, and running the program
* Classes used and their functionalities
* Error handling and invalid input
* Tests

<h2> Introduction </h2>

The Logistics Program is the final project for the course ENSF 409 given by Dr. Ann Barcomb and Emily Marasco at the University of Calgary. The purpose of the project is to create a program that finds the best food item combination from a given database for a number of user inputted clients. 

The program should be able to handle multiple hampers and orders and the given database should update as hampers are created. The program should also create a ext file with the orderlist once an order has been created.

<h2> Installing, compiling, and running the program </h2>

You can download the program from the Group 30: Project - Final Package dropbox in the ENSF 409 course on D2L or you can alternatively download it from the github repository for the program.

To compile the program, first open your computer's terminal. Then, go to the directory where the program file is located. You can move between directories using the command cd [FOLDER].

After moving to the right directory, type the command javac edu/ucalgary/ensf409/*.java, this will compile the program classes that exist within the edu.ucalgary.ensf409 package.

After compiling the program, you can run it by typing the command java edu/ucalgary/ensf409/GUI.java. Once you run the command, a graphical user interface will pop up and you'll be able to use the program.

<h2> Classes used and their functionalities </h2>

The program uses the following classes: Algorithm, Database, Person, Family, Order, GUI, and OutputToFile.

<h4> Algorithm </h4>

The purpose of the algorithm class is to find the best hamper possible given a database of food items.

The Algorithm class does this by generating every single possible combination of the food items that exist within the database and choose the combination that satisfies the family's minimum calories but makes the least waste.

<h4> Database </h4>

The purpose of the Database class is to be able to import food item and client values onto the program. The Database class does this by using mysql queries and statements to select and delete items from the database.

Note: for the Database class to work, a food_inventory database and a user account with <i> student </i> as the username and <i> ensf </i> as the password must exist.

<h4> Person </h4>

The purpose of the Person class is to be able to save the data of a client of a specific client ID. This class is needed as a Family class contains must contain multiple clients.

<h4> Family </h4>

The purpose of the Family class is store a group of person objects and to create the best hamper for these person objects. The class initializes an Algorithm object to find the best hamper possible and stores it in a variable.

<h4> Order </h4>

The purpose of the oOder class is to create a family class for each hamper and stores them into a family array. The order class is also responsible for deleting all the items after each best hamper is found.

<h4> GUI </h4>

The purpose of the GUI class is to provide the user with a GUI so that they could input their hamper and order details and for the best hampers to get displayed to them. The GUI is essentially the main class of the program, so this class needs to run first so that the program could work properly. The GUI class also displays the best hampers once an order is finished.

<h4> OutputToFile </h4>

The purpose of the OutputToFile class is to output a given order as a formatted text file. The class does this by creating a formatting an Order object and inputting it onto a text file. The text file's name will be orderform-[ORDER ID], where [ORDER ID] is a randomly generated 8-digit number.

<h2> Error handling and invalid input </h2>

The main error handling of the user inputs is in the GUI class. The GUI class checks if the the inputs are valid before inserting the inputs into their respective variables. For an input to be valid, the input must be a number that is bigger than -1.

Aside from user inputs, each class validates the its constructor variable or variables if any exist. This is done by either validating the variables within the constructor or through a validation method.

<h2> Tests </h2>

Along with the program, tests are provided from the following classes: Algorithm, Family, Order, Person, and OutputToFile. These tests are provided to check if the functionality of each class works properly or not.

Note: Some getters (namely getOrderCompleted in the Order class and getNutrition in the Person class) could not be fully tested as their values depends on the state of the database.
