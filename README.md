# RailConnect

RailConnect is my Java console project for train ticket booking. I made this project to practice core Java, OOP concepts, and menu-driven program flow.

## What This Project Does

In this app, a user can:

- User registration and login
- Train search by source and destination
- Ticket booking with seat availability checks
- Viewing tickets for the logged-in user
- Ticket cancellation with seat restoration
- Viewing all available trains

The app runs in terminal using a menu. Data is stored in memory, so everything resets when the program is closed.

## Tech Stack

- Java (Core Java)
- Scanner for input
- OOP classes and service-based structure

## Project Structure

- IRCTCAPP.java: main class with menu flow
- UserService.java: handles register/login/logout
- BookingService.java: handles search, booking, and cancellation
- User.java: user details model
- Train.java: train details and seat handling
- Ticket.java: ticket details model

## Features I Focused On

- Simple and clear menu flow
- Input validation for numbers and seat count
- Separate classes for user, train, and ticket
- Separate service classes for logic
- Basic error handling messages

## Getting Started

### Prerequisites

- Java 11 or above
- Terminal (CMD / PowerShell)

### Compile

Run from the project root:

```bash
javac *.java
```

### Run

```bash
java IRCTCAPP
```

## Usage Flow

1. Run the program.
2. Register a user.
3. Login with same username and password.
4. Search trains by source and destination.
5. Book ticket by entering train ID and seat count.
6. View your tickets or cancel a ticket.

## Example Cases

- Booking fails if seat count is invalid.
- Booking fails if seats are not available.
- Cancel ticket adds seats back to the train.
- "View My Tickets" shows only logged-in user's tickets.

## Current Limitations

- Data is in-memory and resets when the program exits.
- No persistent database storage yet.
- Single-user runtime session model.

## Suggested Next Improvements

- Save data in file or database
- Store password in hashed form
- Add unit testing
- Add admin panel for train management
- Add travel date and PNR search

## Author

Asif
