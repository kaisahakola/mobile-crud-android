package fi.tuni.crudappandroid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

/**
 * This class represents the user inside the array.
 *
 * @param firstName The first name of the user.
 * @param lastName The last name of the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(var firstName: String? = null,
                  val lastName: String? = null) : Serializable {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}