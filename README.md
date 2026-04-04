# RailConnect

RailConnect is a simple console-based train ticket booking application built using Core Java.
I created this project to strengthen my understanding of object-oriented programming, class design, and handling real-world logic in a menu-driven CLI application.

## Features
- **User Registration & Login:** Users can create an account and log in securely before accessing the system.
- **Train Search:** Search for available trains by entering source and destination stations.
- **Ticket Booking:** Book tickets by selecting a train and specifying the number of seats.
- **View My Tickets:** View all tickets booked under the logged-in user.
- **Cancel Ticket:** Cancel any booked ticket, and the seats are automatically returned to availability.
- **View All Trains:** Display a list of all trains along with their details.

## Project Structure
```text
RailConnect/
├── RailConnect.java        # Main class (entry point and menu handling)
├── UserService.java        # Handles user registration, login, and session
├── BookingService.java     # Handles booking, searching, and cancellations
├── User.java               # User data model
├── Train.java              # Train data model
└── Ticket.java             # Ticket data model
```

## Tech Stack
- **Language:** Java (Core Java, Java 11+)
- **Concepts Used:** OOP (Encapsulation, Separation of Concerns)
- **Input Handling:** Scanner (`java.util`)

## Getting Started

### Prerequisites
- Java 11 or above installed
- Any terminal (CMD, PowerShell, etc.)

### Compile
```bash
javac *.java
```

### Run
```bash
java RailConnect
```

## How to Use
1. Start the application.
2. Register a new account.
3. Log in using your credentials.
4. Use the menu to:
   - Search for trains
   - Book tickets
   - View your bookings
   - Cancel tickets
   - View all trains
5. Logout or exit when done.

## Sample Behaviors
- Booking 0 seats → Not allowed
- Booking more seats than available → Fails
- Cancelling a ticket → Seats are restored
- Wrong login credentials → Access denied

## Limitations
- No data persistence (everything resets after exit)
- Only one user can use the system at a time
- No database integration (purely in-memory)
