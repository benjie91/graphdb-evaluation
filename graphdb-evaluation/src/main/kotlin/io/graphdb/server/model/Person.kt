package io.graphdb.server.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

typealias PersonId = UInt

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Person(
    @JsonProperty("personId") val personId: PersonId,
    @JsonProperty("latitude") val latitude: String,
    @JsonProperty("longitude") val longitude: String
) : Serializable
