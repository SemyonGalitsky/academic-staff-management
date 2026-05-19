# Academic Staff Management System

A Java-based application built to manage the academic staff, departments, and committees of an educational institution. This project demonstrates core Object-Oriented Programming (OOP) principles, shifting from a procedural architecture to a fully encapsulated, class-driven design.

## Features

* **Lecturer Management:** Track faculty members with details including Name, ID, Academic Title (First, Second, Dr., Prof.), and Salary.
* **Department Organization:** Group lecturers into distinct departments and track student enrollment numbers.
* **Committee Allocation:** Form academic committees with strict constraints, such as requiring the Chairman to hold a minimum title of "Dr."
* **Bidirectional Tracking:** Seamlessly view which committees a lecturer belongs to directly from their profile.
* **Financial Reporting:** Calculate and display the average salary across the entire institution or filter by specific departments.
* **Data Validation:** Built-in checks to prevent duplicate assignments, ensure valid title constraints, and verify records before modification.

## Tech Stack & Architecture

* **Language:** Java
* **Design Principles:** Object-Oriented Programming (OOP)
* **Core Concepts:** Encapsulation, Enums (for academic titles), Constructors, Object references, and dynamic array management.
* **Structure:** Engineered with a centralized College management class to separate business logic and state preservation from the main execution thread.

## How to Run

1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE (such as IntelliJ IDEA or Eclipse).
3. Compile and run the main application file.
4. Follow the interactive console menu to manage the system data.

## Authors

* Semyon Galitsky
* Dor Mendelovich
