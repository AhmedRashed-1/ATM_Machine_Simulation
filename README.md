# ATM Machine Simulation

A console-based ATM simulation written in Java.

## Requirements
- Java 8 or later
- A terminal or Java IDE (such as IntelliJ IDEA)

## How to run
Compile:
```bash
javac src/Main.java
```

Run:
```bash
java -cp src Main
```

## Features
- PIN authentication with three attempts (PIN: `1234`)
- Check balance
- Deposit and withdraw money with input validation
- Display account status (VIP, Regular, or Low Balance)
- Count successful deposit and withdrawal transactions
- Warn when the account balance reaches zero
