# App Architecture - AT&T Car Unlock Demo

## 0. AI Assistant Guidelines
*   **Approval Required:** The AI Assistant must talk through every proposed step and wait for explicit user approval before modifying any code or project files.
*   **Source of Truth:** This file (`docs/ARCHITECTURE.md`) is the primary reference for high-level architecture and requirements. The Assistant must check this file before starting any new task.
*   **Output Structure:** All responses from the AI Assistant must follow this structure:
    1. **Explanations:** Contextual information about the task or problem.
    2. **Recommended Actions:** Specific steps the Assistant proposes to take.
    3. **Decision Needed:** Questions or choices for the user to approve.

## 1. Overview
*   **App Name:** AdistAppDemo
*   **Purpose:** A demo app to simulate remote vehicle control (locking/unlocking) via an AWS EC2 server. Part of a larger 5G connectivity demo involving a Raspberry Pi controller.
*   **Target Audience:** AT&T Connected Solutions - Devices & SIM Team
*   **System Components:**
    1. **Android App:** (This project) User interface for login and command execution.
    2. **AWS EC2 Server:** Middleware that validates users, manages VIN assignments, and forwards commands.
    3. **Raspberry Pi + 5G Module:** End-node that receives commands from EC2 and interacts with the hardware.

## 2. High-Level Architecture
We will follow modern Android development practices:
*   **UI Framework:** Jetpack Compose (Declarative UI)
*   **Architecture Pattern:** MVVM (Model-View-ViewModel)
*   **Language:** Kotlin
*   **Concurrency:** Kotlin Coroutines & Flow (for async API calls)
*   **Dependency Injection:** Hilt (for managing API clients and repositories)
*   **Navigation:** Compose Navigation (Login -> Vehicle Selection -> Control Panel)

## 3. Core Features
*   **Secure Login:**
    *   Mock login screen UI.
    *   Credential validation against the AWS EC2 server.
    *   Token-based authentication (JWT or similar) for subsequent requests.
*   **Vehicle Selection:**
    *   Fetch list of assigned VINs from the server for the logged-in user.
    *   Select a specific VIN to control.
*   **Remote Commands:**
    *   "Lock" door request (HTTPS POST to EC2).
    *   "Unlock" door request (HTTPS POST to EC2).
    *   Success/Failure feedback from the server.

## 4. Tech Stack & Libraries
*   **Networking:** Retrofit + OkHttp (HTTPS communication with EC2).
*   **Serialization:** Kotlinx Serialization (JSON parsing).
*   **DI:** Hilt (to provide Singleton instances of the API service).
*   **State Management:** StateFlow in ViewModels.

### 4.1 API Contract
The detailed JSON request/response structures are defined in [API.md](API.md).

## 5. UI/UX Principles
### 5.1 Design Principles  
* **Material Design 3:** Standard modern look.
*   **Visual Feedback:** Clear loading states and confirmation snackbars/dialogs for commands.
*   **Branding:** AT&T theme colors.
    * Blue: #009FDB
    * White: #FFFFFF

### 5.2 Navigation & Screens
1.  **LoginScreen:** Username and password input.
2.  **VehicleSelectionScreen:** A list or dropdown of available vehicles (VINs).
3.  **ControlPanelScreen:** Large, intuitive "Lock" and "Unlock" buttons for the selected vehicle.

## 6. Data Model
*   **User:** `userId`, `username`, `authToken`.
*   **Vehicle:** `vin`, `name`.
*   **CommandRequest:** `vin`, `action` (LOCK/UNLOCK).

## 7. Version Control & Commits
*   **Recommendation Trigger:** The AI Assistant will recommend a git commit after the successful completion of a logical block of work (e.g., a new feature, a data model, or a UI screen).
*   **Stability Check:** Commits should only be recommended once the project builds successfully.
*   **Message Style:** Commit messages should be concise and describe the "what" and "why" of the change.
