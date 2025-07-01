# 🏨 Hotel Management System Project

A Java-based **Hotel Management System** designed to automate hotel operations including guest registration, room booking, housekeeping management, and staff access. This project features a modern JavaFX user interface and demonstrates core OOP concepts.

---

## 🚀 Features

- 🔐 **Authentication System**  
  - Login/Register with role-based access (Guest or Employee)  
  - File-based user data storage  

- 🛏️ **Room Management**  
  - Room types: `StandardRoom` and `HighRoom`  
  - Dynamic room availability and pricing  
  - Reservation history tracking  

- 👤 **Guest Functionalities**  
  - View available rooms  
  - Book or cancel reservations  
  - Check total days of stay and price  

- 🧹 **Employee Modules**  
  - Housekeeping can update room cleanliness status  
  - Receptionist can manage reservations and guest records  

- 💾 **Data Persistence**  
  - File-based storage for users, rooms, and reservations  
  - Read/write and CRUD operations handled via file I/O  

- 🎨 **Modern JavaFX UI**  
  - Clean and responsive interface using FXML and CSS  
  - Scene-based navigation with consistent styling  

---

## 📂 Project Structure

Hotel-Management-System-Project/
├── src/
│ ├── controllers/ # JavaFX controller classes
│ ├── models/ # Core classes: Guest, Room, etc.
│ ├── utils/ # File handling and helper functions
│ └── Main.java # Main entry point
│
├── resources/
│ ├── fxml/ # UI design files (FXML)
│ └── css/ # Custom JavaFX styles
│
├── data/ # Persistent storage for users and reservations
└── README.md

---

## 💡 Technologies & Concepts

- Java 11+
- JavaFX (FXML, Scene Builder)
- Object-Oriented Programming  
- File I/O Handling  
- Exception Handling  
- Multithreading *(optional/planned)*  

---
