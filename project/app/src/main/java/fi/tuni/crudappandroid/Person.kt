package fi.tuni.crudappandroid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(var firstName: String? = null,
                  val lastName: String? = null) : Serializable {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}