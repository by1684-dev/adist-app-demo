# App Design Requirements - AT&T Car Unlock Demo

## 1. Overview
*   **App Name:** AdistAppDemo
*   **Purpose:** A demo application to simulate remote vehicle control (locking/unlocking) via an AWS EC2 server. Part of a larger 5G connectivity demo involving a Raspberry Pi controller.
*   **Target Audience:** AT&T Demo Stakeholders / Technical Evaluators.
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

## 5. UI/UX Principles
*   **Material Design 3:** Standard modern look.
*   **Visual Feedback:** Clear loading states and confirmation snackbars/dialogs for commands.
*   **Branding:** AT&T theme colors.

## 6. Data Model
*   **User:** `userId`, `username`, `authToken`.
*   **Vehicle:** `vin`, `modelName`.
*   **CommandRequest:** `vin`, `action` (LOCK/UNLOCK).
