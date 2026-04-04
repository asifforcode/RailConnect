# RailConnect

RailConnect is a console-based train ticket booking application developed in Core Java.
Built to practice object-oriented programming, class separation, and building menu-driven CLI tools.

---

## Features

- **User Registration & Login** — Create an account and securely log in before accessing any features.
- **Train Search** — Search trains by entering source and destination stations.
- **Ticket Booking** — Choose a train and book your preferred number of seats.
- **View My Tickets** — See a list of all tickets booked under your account.
- **Cancel Ticket** — Cancel a specific ticket and get your seats restored to the train.
- **List All Trains** — Browse the full list of available trains.

All data is stored in memory during the session. The application resets when closed.

---

## Project Structure

```
RailConnect/
├── RailConnect.java        # Entry point — controls menu flow and user interaction
├── UserService.java        # Handles registration, login, and session management
├── BookingService.java     # Manages train search, ticket booking, and cancellation
├── User.java               # User model — stores account details
├── Train.java              # Train model — stores route info and seat availability
└── Ticket.java             # Ticket model — stores booking details
```

---

## Tech Stack

- **Language:** Core Java (Java 11+)
- **Input:** java.util.Scanner
- **Design:** Object-Oriented Programming with clear class separation

---

## Getting Started

### Prerequisites

- Java 11 or later installed
- Terminal (CMD, PowerShell, or any shell)

### Compile

```bash
javac *.java
```

### Run

```bash
java RailConnect
```

---

## How It Works

1. Launch the application — a welcome banner appears.
2. **Register** a new account using a unique username and password.
3. **Login** with your credentials.
4. From the main menu, you can:
   - Search trains by route
   - Book tickets by entering a Train ID and seat count
   - View all your booked tickets
   - Cancel a ticket using its Ticket ID
   - Browse all available trains
5. **Logout** or choose **Exit** to close the app.

---

## Sample Scenarios

| Scenario | Result |
|---|---|
| Book with 0 seats | Rejected with an error |
| Book more seats than available | Booking fails |
| Cancel a valid ticket | Seats restored to train |
| View tickets after cancellation | Cancelled ticket removed from list |
| Login with wrong password | Access denied |

---

## Limitations

- Data does not persist — all records reset when the app is closed.
- Only one user session is active at a time.
- No database or file storage is used.
