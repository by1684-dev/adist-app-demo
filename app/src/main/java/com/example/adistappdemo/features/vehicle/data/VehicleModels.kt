package com.example.adistappdemo.features.vehicle.data

import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val vin: String,
    val name: String
)

@Serializable
data class CommandRequest(
    val vin: String,
    val action: String
)

@Serializable
data class CommandResponse(
    val status: String,
    val message: String,
    val timestamp: String
)
