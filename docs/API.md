# API Specification - AT&T Car Unlock Demo

This document defines the JSON contract between the Android App and the AWS EC2 Server.

## 1. Authentication (POST /auth/login)
**Request Body:**
```json
{
  "username": "user_att",
  "password": "password123"
}
```

**Response Body (Success):**
```json
{
  "token": "jwt_token_string",
  "userId": "uuid_12345",
  "username": "AT&T Demo User"
}
```

## 2. Vehicle Information (GET /vehicles)
**Headers:**
* `Authorization: Bearer <token>`

**Response Body (List):**
```json
[
  {
    "vin": "1A2B3C4D5E6F7G8H9",
    "name": "2025 AT&T Connected Truck"
  },
  {
    "vin": "9H8G7F6E5D4C3B2A1",
    "name": "Demo Fleet Sedan"
  }
]
```

## 3. Command Execution (POST /vehicle/command)
**Headers:**
* `Authorization: Bearer <token>`

**Request Body:**
```json
{
  "vin": "1A2B3C4D5E6F7G8H9",
  "action": "LOCK" 
}
```
*Note: action is a String: "LOCK" or "UNLOCK"*

**Response Body:**
```json
{
  "status": "SUCCESS",
  "message": "Unlock command sent to vehicle",
  "timestamp": "2023-10-27T10:00:00Z"
}
```
