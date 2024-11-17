package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (wine in wines) {
            updatePrice(wine)
            handleExpiration(wine)
            ensurePositivePrices(wine)
            ensurePriceTreshold(wine)
        }
    }

    private fun updatePrice(wine: Wine) {
        // cellar or aging wine prices increases in time, any other decreases
        if (!wine.isConservato() && !wine.isEvent()) {
            decreasePrice(wine)
        } else {
            increasePrice(wine)
        }
    }

    private fun increasePrice(wine: Wine) {
        if (wine.price < 100) {
            wine.price += 1

            if (wine.isEvent()) {
                when {
                    wine.expiresInYears < 3 -> wine.price += 2
                    wine.expiresInYears < 8 -> wine.price += 1
                }
            }
        }
    }

    private fun decreasePrice(wine: Wine) {
        if (wine.price > 0) {
            when {
                wine.isAlexanderTheGreatWine() -> Unit
                wine.isEcoBrilliantWine() -> wine.price -= 2
                else -> wine.price -= 1 // Standard price decrease for other wines
            }
        }
    }

    private fun handleExpiration(wine: Wine) {
        if (!wine.isAlexanderTheGreatWine()) {
            wine.expiresInYears -= 1
        }

        //No handling needed if not expired
        if (wine.expiresInYears >= 0) return

        //Handle the expired wines
        when {
            wine.isConservato() -> increasePrice(wine)
            wine.isEvent() -> wine.price = 0
            wine.isStandardWine() -> decreasePrice(wine)
        }
    }

    private fun ensurePositivePrices(wine: Wine) {
        if (wine.price < 0) wine.price = 0
    }

    private fun ensurePriceTreshold(wine: Wine) {
        if (wine.price > 100 && !wine.isAlexanderTheGreatWine()) {
            wine.price = 100
        }
    }

    private fun Wine.isStandardWine() = name.contains("Standard")
    private fun Wine.isConservato() = name.contains("Conservato")
    private fun Wine.isEvent() = name.startsWith("Event")
    private fun Wine.isAlexanderTheGreatWine() = name.contains("Wine brewed by Alexander the Great")
    private fun Wine.isEcoBrilliantWine() = name.contains("Eco Brilliant")

}