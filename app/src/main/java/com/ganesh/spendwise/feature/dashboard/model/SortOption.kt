package com.ganesh.spendwise.feature.dashboard.model


enum class SortOption(
    val buttonLabel: String,
    val menuLabel: String
) {
    DATE_DESC(
        "Latest",
        "Lastest First"
    ),
    DATE_ASC(
        "Oldest",
        "Oldest First"
    ),
    AMOUNT_DESC(
        "Highest",
        "Highest Amount"
    ),
    AMOUNT_ASC(
        "Lowest",
        "Lowest Amount"
    )
}