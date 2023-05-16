package fi.tuni.crudappandroid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * This class is the array containing the users.
 *
 * @param users A list of Person objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonJsonObject(var users: MutableList<Person>? = null)