package com.compose.testing.canvas

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Item(
    val name: String,
    val due: String,
    val amount: Float,
    val color: Color
)

object UserData {
    val items: List<Item> = listOf(
        Item(
            "RedPay Credit",
            "Jan 29",
            45.36f,
            Color(0xFFFFDC78)
        ),
        Item(
            "Rent",
            "Feb 9",
            1200f,
            Color(0xFFFF6951)
        ),
        Item(
            "TabFine Credit",
            "Feb 22",
            87.33f,
            Color(0xFFFFD7D0)
        ),
        Item(
            "ABC Loans",
            "Feb 29",
            400f,
            Color(0xFFFFAC12)
        ),
        Item(
            "ABC Loans 2",
            "Feb 29",
            77.4f,
            Color(0xFFFFAC12)
        )
    )
}

fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = this.sumOf { selector(it).toDouble() }
    return this.map { (selector(it) / total).toFloat() }
}

