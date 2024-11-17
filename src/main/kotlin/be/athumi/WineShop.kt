package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (wine in wines) {

            if (!wine.isConservato() && !wine.isEvent()) {
                decreasePrice(wine)
            } else {
                if (wine.price < 100) {
                    wine.price += 1

                    if (wine.isEvent()) {
                        if (wine.expiresInYears < 8) {
                            increasePrice(wine, 1)
                        }

                        if (wine.expiresInYears < 3) {
                            increasePrice(wine, 2)
                        }
                    }
                }
            }

            if (!wine.isAlexanderTheGreatWine()) {
                wine.expiresInYears -= 1
            }

            if (wine.expiresInYears < 0) {
                if (!wine.isConservato()) {
                    if (!wine.isEvent()) {
                        decreasePrice(wine)
                    } else {
                        wine.price = 0
                    }
                } else {
                    increasePrice(wine, 1)
                }
            }

            ensurePositivePrices(wine)
        }
    }

    private fun increasePrice(wine: Wine, amount: Int) {
        if (wine.price < 100) {
            wine.price += amount
        }
    }

    private fun decreasePrice(wine: Wine) {
        if (wine.price > 0) {
            if (!wine.isAlexanderTheGreatWine()) {
                wine.price -= 1
            }
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