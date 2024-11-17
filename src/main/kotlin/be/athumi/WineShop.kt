package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (wine in wines) {

            if (!wine.isConservato() && !wine.isEvent()) {
                decreasePrice(wine)
            } else {
                increasePrice(wine)
            }

            handleExpiration(wine)
            ensurePositivePrices(wine)
        }
    }

    private fun increasePrice(wine: Wine) {
        if (wine.price < 100) wine.price += 1

        if (wine.isEvent()) {
            when {
                wine.expiresInYears < 3 && wine.price < 100 -> wine.price += 2
                wine.expiresInYears < 8 && wine.price < 100 -> wine.price += 1
            }
        }

    }

    private fun decreasePrice(wine: Wine) {
        if (wine.price > 0) {
            if (!wine.isAlexanderTheGreatWine()) {
                wine.price -= 1
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

    private fun Wine.isStandardWine(): Boolean {
        return !isConservato() && !isEvent() && !isAlexanderTheGreatWine()
    }

    private fun Wine.isConservato(): Boolean {
        return name.contains("Conservato")
    }

    private fun Wine.isEvent(): Boolean {
        return name.startsWith("Event")
    }

    private fun Wine.isAlexanderTheGreatWine(): Boolean {
        return name == "Wine brewed by Alexander the Great"
    }
}