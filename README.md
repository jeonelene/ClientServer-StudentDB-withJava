# Client-Server Student Database Java App

This Java application implements a client-server architecture for managing a student database. The server interacts with a MariaDB database to perform operations such as searching for a student and adding a new student.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- MariaDB Server
- MariaDB JDBC Driver (included in the project)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/jeonelene/ClientServer-StudentDB-withJava
    cd clientserver-studentdb-javaapp
    ```

2. Compile the source code:

    ```bash
    javac -cp ".:path/to/mariadb-java-client.jar" *.java
    ```

3. Run the server:

    ```bash
    java -cp ".:path/to/mariadb-java-client.jar" clientserver.studentdb.javaapp.Server
    ```

4. Run the client:

    ```bash
    java -cp ".:path/to/mariadb-java-client.jar" clientserver.studentdb.javaapp.Client
    ```

## Usage

1. Ensure the server is running before starting the client.
2. The client provides a simple menu to search for a student, add a new student, or exit the application.

## Features

- **Search for a Student:** Retrieve student information by entering the surname.
- **Add a New Student:** Add a new student to the database.

## Configuration

- Database connection details are configured in the `Server` class. Modify the JDBC URL, username, and password as needed.

## Contributing

Contributions are welcome. If you find any issues or have improvements to suggest, please open an issue or create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
