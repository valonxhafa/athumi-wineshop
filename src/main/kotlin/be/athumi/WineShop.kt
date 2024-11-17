package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (wine in wines) {
            if (!wine.isConservato() && !wine.isEvent()) {
                if (wine.price > 0) {
                    if (!wine.isAlexanderTheGreatWine()) {
                        wine.price -= 1
                    }
                }
            } else {
                if (wine.price < 100) {
                    wine.price += 1

                    if (wine.isEvent()) {
                        if (wine.expiresInYears < 8) {
                            if (wine.price < 100) {
                                wine.price += 1
                            }
                        }

                        if (wine.expiresInYears < 3) {
                            if (wine.price < 100) {
                                wine.price += 2
                            }
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
                        if (wine.price > 0) {
                            if (!wine.isAlexanderTheGreatWine()) {
                                wine.price -= 1
                            }
                        }
                    } else {
                        wine.price = 0
                    }
                } else {
                    if (wine.price < 100) {
                        wine.price += 1
                    }
                }
            }

            ensurePositivePrices(wine)
        }
    }

    private fun ensurePositivePrices(wine: Wine) {
        if (wine.price < 0) wine.price = 0
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