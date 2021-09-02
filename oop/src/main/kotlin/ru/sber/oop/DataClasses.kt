package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is User) return false
        var ret = other.age == age && other.name == name

        if (::city.isInitialized && other::city.isInitialized) {
            ret = ret && other.city == city
        } else {
            ret = ret && !::city.isInitialized && !other::city.isInitialized
        }

        return ret
    }

    override fun hashCode(): Int {
        return city.hashCode() xor name.hashCode() xor age.hashCode()
    }
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "Ivan")

    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"

    println(user1.equals(user3))

    println(user1.hashCode())
    println(user3.hashCode())
}