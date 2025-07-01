🏨 Hotel Management System Project
A Java-based Hotel Management System designed to streamline and automate hotel operations such as guest registration, room reservations, housekeeping coordination, and staff access management. Built with JavaFX for the user interface, this project demonstrates core object-oriented programming principles and real-world application design.

🚀 Features
🔐 Authentication System

Secure Login/Register screens with role-based access (Guest or Employee).

Persistent user data storage using files.

🛏️ Room Management

Different room types: StandardRoom and HighRoom.

Dynamic room availability and price calculation.

Reservation history tracking.

👤 Guest Functionalities

Check room availability.

Reserve or cancel a room.

View total days of stay and total cost.

🧹 Housekeeping & Receptionist Modules

Staff can update room cleanliness status.

Receptionists can manage reservations and view guest records.

💾 Data Persistence

Data saved and retrieved from local files.

Supports CRUD operations for users, reservations, and rooms.

🎨 Modern UI with JavaFX

Clean and user-friendly design.

Multiple scenes for different roles and functionalities.

📂 Project Structure
graphql
Copy
Edit
Hotel-Management-System-Project/
│
├── src/
│   ├── controllers/        # JavaFX controller classes
│   ├── models/             # Guest, Room, Reservation, Employee classes
│   ├── utils/              # File handling, data validation, utilities
│   ├── Main.java           # Main entry point
│
├── resources/
│   ├── fxml/               # FXML files for UI design
│   ├── css/                # Styling for JavaFX UI
│
├── data/                   # User and reservation data files
├── README.md
🧠 Concepts Used
Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)

JavaFX GUI Development

File Handling in Java

Exception Handling

Multithreading (optional or planned)

💡 How to Run
Clone the repository:

bash
Copy
Edit
git clone https://github.com/mohamedhishm/Hotel-Management-System-Project.git
Open the project in an IDE like IntelliJ IDEA or Eclipse.

Make sure JavaFX is configured in your environment.

Run Main.java.

📸 Screenshots
(You can insert screenshots of login screen, room reservation form, etc. here)

🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change or improve.

📧 Contact
For inquiries or feedback, contact mohamedabnhisham@gmail.com
