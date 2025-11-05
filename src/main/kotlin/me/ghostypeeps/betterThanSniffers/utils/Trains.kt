package me.ghostypeeps.betterThanSniffers.utils

import java.time.LocalDate

/**
 * trains haha
 */
object Trains {
        fun isTrains() : Boolean {
            val now = LocalDate.now()
            val year = now.year
            val start = LocalDate.of(year, 4, 1)
            val end = LocalDate.of(year, 4, 30)

            return now.isAfter(start) && now.isBefore(end)
        }
}