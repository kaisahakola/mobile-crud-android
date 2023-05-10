package fi.tuni.crudappandroid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonJsonObject(var users: MutableList<Person>? = null)