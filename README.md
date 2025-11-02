🎫 Ticket Booking System — Java Console Application

A Java-based ticket booking console application that simulates real-world train ticket management — including user registration, login, train search, ticket booking, and cancellation.
This project demonstrates practical implementation of OOP concepts, Java 8 functional programming, data persistence using JSON, and Gradle-based project structuring.

🚀 Features

✅ User Registration & Login

Secure password storage using BCrypt hashing.

Validation for duplicate usernames and incorrect passwords.

✅ Train Search & Listing

Search trains by source and destination.

Displays train details, timings, and availability.

✅ Ticket Booking

Seats managed via a dynamic 2D seatMatrix.

Automatically assigns an available seat.

Generates unique Ticket IDs using UUID.

✅ Ticket Cancellation

Cancels booked tickets and frees the corresponding seat.

Updates both users.json and trains.json automatically.

✅ Persistent Data Storage

Uses Jackson ObjectMapper for reading/writing JSON files.

Keeps user and train data synced between sessions.

⚙️ Tech Stack
Component	Technology
Language	Java 17+
Build Tool	Gradle
Data Storage	JSON files (users.json, trains.json)
Libraries	Jackson (JSON), BCrypt (password hashing)
🧩 Core Concepts Demonstrated
Concept	Description
Object-Oriented Design	Entities (User, Train, Ticket) with service layers for clean separation of logic.
Functional Programming	Streams, Optionals, Filters, Maps for cleaner, null-safe code.
BCrypt Hashing	Secure password encryption and verification.
TypeReference (Jackson)	Deserialize generic types like List<User> or List<Train>.
UUID Generation	Unique identifiers for tickets and users.
Data Persistence	File I/O synchronization using Jackson’s ObjectMapper.

🧠 Example Workflow
1️⃣ Register a User
Enter name: arsh
Enter age: 24
Enter password: pass123
✅ User registered successfully!

2️⃣ Login
Enter username: arsh
Enter password: pass123
✅ Login successful!

3️⃣ Search and Book Ticket
Enter Source Station: Mumbai
Enter Destination Station: Pune

Available Trains:
- Express Line (12345)
- Superfast (45678)

Select Train: 12345
✅ Ticket booked successfully!
Ticket ID: T001
Seat: Row 1, Column 2

4️⃣ Cancel Ticket
Enter Ticket ID: T001
✅ Ticket cancelled successfully!
Refund initiated for ₹250.

💾 Data Persistence Format
users.json
[
  {
    "userId": "U001",
    "userName": "arsh",
    "age": "24",
    "sex": "Male",
    "password": "hashed_password_here",
    "ticketsBooked": [
      {
        "ticketId": "T001",
        "trainNo": "12345",
        "source": "Mumbai",
        "destination": "Pune"
      }
    ]
  }
]

trains.json
[
  {
    "trainName": "Express Line",
    "trainNo": "12345",
    "sourceStation": "Mumbai",
    "destinationStation": "Pune",
    "departureTimeSourceStation": "08:30:00",
    "arrivalTimeSourceStation": "11:00:00",
    "arrivesNextDay": false,
    "seatMatrix": [
      [1, 0, 0, 0],
      [0, 0, 0, 0]
    ]
  }
]

🏗️ Build & Run Instructions
Prerequisites

Java 17+

Gradle 8+

1️⃣ Clone the repository
git clone https://github.com/<your-username>/Ticket-Booking-System.git
cd Ticket-Booking-System

2️⃣ Build the project
gradle build

3️⃣ Run the program
gradle run


Or if you have a main class defined:

java -cp build/classes/java/main ticket.booking.Main


🧩 Key Learnings

Implemented secure user authentication using BCrypt.

Learned to use Optional and Stream API for concise, null-safe code.

Explored Jackson’s TypeReference for deserializing generic JSON lists.

Gained hands-on experience with UUID generation, Gradle structure, and functional-style data filtering.

Understood how to design a modular console-based architecture in Java.


🚀 Future Enhancements

 Add Admin features to manage trains and users.

 Integrate with a SQL database instead of JSON.

 Build a Spring Boot REST API on top of the logic.

 Add a frontend UI using React or JavaFX.


🤝 Contributing

Contributions are welcome!
Feel free to open issues, suggest new features, or submit pull requests.

🧑‍💻 Author

Arsh Ghaiwat
Associate System Engineer | Java Full Stack Learner
📚 Passionate about writing clean, modular, and scalable Java applications.
