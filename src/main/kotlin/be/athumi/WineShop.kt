package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (wine in wines) {
            ensurePositivePrices(wine)
            ensurePriceTreshold(wine)

            updatePrice(wine)
            handleExpiration(wine)
        }
    }

    private fun updatePrice(wine: Wine) {
        // cellar or aging wine prices increases in time, any other decreases
        if (wine.isConservato() || wine.isEvent()) {
            increasePrice(wine)
        } else {
            decreasePrice(wine)
        }
    }

    private fun increasePrice(wine: Wine) {
        wine.price += 1

        if (wine.isEvent()) {
            when {
                wine.expiresInYears < 3 -> wine.price += 2
                wine.expiresInYears < 8 -> wine.price += 1
            }
        }

    }

    private fun decreasePrice(wine: Wine) {

        when {
            wine.isAlexanderTheGreatWine() -> Unit //Can't decrease
            wine.isEcoBrilliantWine() -> wine.price -= 2 //Decreases twice as fast
            else -> wine.price -= 1
        }

    }

    private fun handleExpiration(wine: Wine) {
        //Default expiration
        if (!wine.isAlexanderTheGreatWine()) {
            wine.expiresInYears -= 1
        }

        //No handling needed if not expired
        if (wine.expiresInYears >= 0) return

        //Handle the expired wines
        when {
            wine.isConservato() -> increasePrice(wine)
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