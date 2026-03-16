# RailConnect

RailConnect is a console-based Java railway booking system inspired by real-world ticketing workflows. It is designed as a clean, beginner-friendly project that demonstrates object-oriented design, service separation, and practical booking logic.

## Overview

RailConnect supports the full booking lifecycle:

- User registration and login
- Train search by source and destination
- Ticket booking with seat availability checks
- Viewing tickets for the logged-in user
- Ticket cancellation with seat restoration
- Viewing all available trains

The application runs in an interactive terminal menu and stores data in memory for the current runtime session.

## Tech Stack

- Java (Core Java, standard library only)
- Console/CLI interface using Scanner
- Object-oriented architecture (models + services + app entry)

## Project Structure

- IRCTCAPP.java: Application entry point and menu flow
- UserService.java: User registration, authentication, and session state
- BookingService.java: Train search, booking, cancellation, and ticket retrieval
- User.java: User domain model
- Train.java: Train domain model and seat management
- Ticket.java: Ticket domain model

## Key Design Highlights

- Clear separation of concerns between UI flow and business logic
- Input validation for numeric and booking operations
- Immutable domain state where appropriate for safer models
- Cleaner output formatting for readable console interaction
- Single-responsibility service classes for maintainability

## Getting Started

### Prerequisites

- Java 17+ (or Java 11+ with modern switch expression support)
- Command line terminal (PowerShell, CMD, or similar)

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

1. Start the program.
2. Register a new account.
3. Login with your credentials.
4. Search trains using source and destination stations.
5. Book a ticket by selecting a valid train ID and seat count.
6. View or cancel your booked tickets from the user menu.

## Sample Functional Scenarios

- Booking fails gracefully when seat count is invalid.
- Booking fails when seats are unavailable.
- Cancellation restores seats to the original train.
- Ticket list displays only the current user’s bookings.

## Limitations (Current Version)

- Data is in-memory and resets when the program exits.
- No persistent database storage yet.
- Single-user runtime session model.
