# RailConnect

RailConnect is a Java console-based project for train ticket booking. I built this to practice core Java concepts, OOP, and handling menu-driven programs.

## What This Project Does

This app allows users to:

- Register and log in
- Search trains using source and destination
- Book tickets based on available seats
- View their booked tickets
- Cancel tickets and restore seats
- See all available trains

The application runs in the terminal and uses a simple menu system. All data is stored in memory, so it resets once the program is closed.

## Tech Stack

- Core Java
- Scanner (for user input)
- OOP concepts with separate classes

## Project Structure

- `IRCTCAPP.java` - Main class that controls the menu
- `UserService.java` - Handles user registration and login
- `BookingService.java` - Manages train search, booking, and cancellation
- `User.java` - Stores user details
- `Train.java` - Stores train info and seat availability
- `Ticket.java` - Stores ticket details

## Features

- Simple menu-driven flow
- Basic input validation (like seat count and numeric input)
- Clear separation of logic using different classes
- Error messages for invalid operations

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

## How to Use

1. Run the program
2. Register a new user
3. Log in using the same credentials
4. Search for trains
5. Book tickets by entering train ID and number of seats
6. View or cancel your tickets

## Example Cases

- Booking fails if seat count is invalid
- Booking fails if seats are not available
- Cancelling a ticket adds seats back
- "View My Tickets" only shows tickets for the logged-in user

## Limitations

- Data is not saved permanently
- Everything resets after closing the app
- Only one user can use it at a time
