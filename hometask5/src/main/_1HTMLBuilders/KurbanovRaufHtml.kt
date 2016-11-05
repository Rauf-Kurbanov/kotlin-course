package task

import _1HTMLBuilders.*

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) %2 == 0) "#dce4ff" else "#eff2ff"

/*
1) Fill the table with the proper values from products.
2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
Pass a color as an argument to functions 'tr', 'td'.
You can run the 'Html Demo' configuration in IntelliJ IDEA to see the rendered table.
*/

fun renderProductTable(): String {
    return html {
        table {
            tr (color = getTitleColor()) {
                td {
                    text("Product")
                }
                td {
                    text("Price")
                }
                td {
                    text("Popularity")
                }
            }

            val products = getProducts()
            for ((row, p) in 1..products.size zip products) {
                tr {
                    td (color = getCellColor(row, 0)) {
                        text(p.description)
                    }
                    td (color = getCellColor(row, 1)) {
                        text(p.popularity)
                    }
                    td (color = getCellColor(row, 2)) {
                        text(p.price)
                    }
                }
            }
        }
    }.toString()
}