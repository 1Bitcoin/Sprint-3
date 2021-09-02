package ru.sber.oop

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = (0..10).random()

    fun attack(opponent: Fightable): Int
}

class Player(val name: String, val isBlessed: Boolean,
             override val powerType: String, override var healthPoints: Int) : Fightable {

    override fun attack(opponent: Fightable): Int {
        var totalDamage = damageRoll
        if (isBlessed)
            totalDamage = damageRoll * 2

        opponent.healthPoints -= totalDamage
        return totalDamage
    }
}

abstract class Monster() : Fightable {
    abstract val name: String
    abstract val description: String

    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll
        return damageRoll
    }
}

fun Monster.getSalutation() = "Hello, I'm Monster"

class Goblin(override val name: String, override val description: String,
             override val powerType: String, override var healthPoints: Int) : Monster() {

    override val damageRoll: Int
        get() = super.damageRoll / 2
}