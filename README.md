# 💻 Java CLI File Manager

This is a **Command-Line Interface (CLI)** project built using **Java**. It mimics the functionality of traditional UNIX shell commands such as `ls`, `cd`, `mkdir`, `touch`, `rm`, and supports redirection operators (`>`, `>>`).

---

## 🚀 Features

- ✅ Modular design using the **Command Pattern**
- 📂 File and directory manipulation (`mkdir`, `touch`, `rm`, `rmdir`, `cd`)
- 📃 Content viewing and printing (`cat`, `ls`, `pwd`, `help`)
- ➕ Output redirection (`>` for overwrite, `>>` for append)
- ❌ Exit command to quit
- 🧩 Easily extendable for new commands

---

## 🧱 Class Structure Overview

### 🔁 Command Hierarchy

```
Command (interface)
├── LsCommand
├── RedirectOverwriteCommand
├── RedirectAppendCommand
├── TouchCommand
├── RmdirCommand
├── MkdirCommand
├── ExitCommand
├── CatCommand
├── PwdCommand
├── CdCommand
├── RmCommand
├── HelpCommand
└── MvCommand
```

Each command implements the `execute(String input)` method.

### 🧠 Core Components

- **CLI**: Main class with `main()` method and maintains `currentPath`
- **CommandParser**: Maps and parses commands using a `HashMap<String, Command>`

---

## 🧪 How It Works

1. The user enters a command in the CLI.
2. `CommandParser` parses the input and returns the appropriate `Command` object.
3. The `execute(String input)` method runs with arguments parsed from the input string.

---

## 🧩 Sample Commands

```bash
ls
ls -a
pwd
mkdir folderName
cd folderName
touch file.txt
cat file.txt
rm file.txt
rmdir folderName
mv old.txt new.txt
exit
```

Supports:
- `>` : Redirect output and overwrite
- `>>` : Redirect output and append

---

## 🛠️ Setup Instructions

### Prerequisites

- Java JDK 8+
- IDE like IntelliJ IDEA or VS Code with Java support

### Steps

```bash
git clone https://github.com/your-username/java-cli-file-manager.git
cd java-cli-file-manager
javac CLI.java
java CLI
```

---

## 🧑‍💻 Developer Notes

- Redirection commands extend base `Command` logic to support file IO
- Each command is decoupled and testable independently
- Use `HelpCommand` to list all available commands and usage

---

## 📄 License

This project is licensed under the MIT License.

---

**© 2025 Mahmoud Sayed. All rights reserved.**
