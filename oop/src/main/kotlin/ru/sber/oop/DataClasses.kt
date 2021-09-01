package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        return other is User && other.city == city && other.age == age && other.name == name
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