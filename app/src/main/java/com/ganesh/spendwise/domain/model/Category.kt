package com.ganesh.spendwise.domain.model

import kotlinx.serialization.Serializable


enum class Category(
    val displayName : String,
    val emoji : String
) {
    FOOD(
        displayName = "Food",
        emoji = "🍔"
    ),

    SHOPPING(
        displayName = "Shopping",
        emoji = "🛒"
    ),

    TRAVEL(
        displayName = "Travel",
        emoji = "✈️"
    ),

    BILLS(
        displayName = "Bills",
        emoji = "💡"
    ),

    HEALTH(
        displayName = "Health",
        emoji = "🏥"
    ),

    ENTERTAINMENT(
        displayName = "Entertainment",
        emoji = "🎬"
    ),

    EDUCATION(
        displayName = "Education",
        emoji = "📚"
    ),

    OTHER(
        displayName = "Other",
        emoji = "📦"
    );

    companion object{
        fun from(value : String) : Category{
            return entries.firstOrNull{
                it.name.equals(value, ignoreCase = true)
            } ?: OTHER
        }

        fun to(value : Category) : String{
            return when(value){
                Category.FOOD ->  "Food"
                Category.BILLS -> "Bills"
                Category.SHOPPING ->  "Shopping"
                Category.TRAVEL -> "Travel"
                Category.HEALTH ->  "Health"
                Category.ENTERTAINMENT -> "Entertainment"
                Category.EDUCATION ->  "Education"
                else -> "Others"

            }
        }
    }
}