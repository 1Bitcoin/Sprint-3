package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = (0..10).random()

    fun attack(opponent: Fightable): Int
}

class Player(val name: String, val isBlessed: Boolean): Fightable {
    override val powerType: String
        get() = TODO("Not yet implemented")

    override var healthPoints: Int = 100

    override fun attack(opponent: Fightable): Int {
        var totalDamage = damageRoll
        if (isBlessed)
            totalDamage = damageRoll * 2

        opponent.healthPoints -= totalDamage
        return totalDamage
    }
}

abstract class Monster(val name: String, val description: String): Fightable {
    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll
        return damageRoll
    }
}

fun Monster.getSalutation() = "Hello, I'm Monster"

class Goblin(): Monster("Zombie", "Bad boy") {
    override val powerType: String
        get() = TODO("Not yet implemented")

    override var healthPoints: Int
        get() = TODO("Not yet implemented")
        set(value) { healthPoints = value }

    override val damageRoll: Int
        get() = super.damageRoll / 2
}