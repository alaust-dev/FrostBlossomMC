package de.alaust.frostblossom.item

import kotlin.random.Random

class DropAmountRange(val min: Int, val max: Int) {
    constructor(amount: Int) : this(amount, amount)

    fun getRandom(): Int = Random.nextInt(min, max)
}