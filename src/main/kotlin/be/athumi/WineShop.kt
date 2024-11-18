package be.athumi

class WineShop(var wines: List<Wine>) {

    fun next() {
        for (wine in wines) {
            updatePrice(wine)
            handleExpiration(wine)
            ensurePriceThresholds(wine)
        }
    }

    private fun updatePrice(wine: Wine) {
        // cellar or aging wine prices increases in time, any other decreases
        if (wine.isCellarOrAgingWine()) {
            increasePrice(wine)
        } else {
            decreasePrice(wine)
        }
    }

    private fun increasePrice(wine: Wine) {
        wine.price += 1
        priceIncreaseForEventWine(wine)
    }

    private fun priceIncreaseForEventWine(wine: Wine) {
        if (wine.isEvent()) {
            when {
                wine.expiresInYears < 3 -> wine.price += 3
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

        //No handling needed if not expired, else update
        if (wine.expiresInYears >= 0) return
        else {
            removeExpiredEventWines(wine)
            updatePrice(wine)
        }

    }

    private fun removeExpiredEventWines(wine: Wine) {
        if(wine.isEvent() && wine.expiresInYears < 0){
            wine.price = 0
            wines = wines.filterNot { it == wine }
        }
    }

    private fun ensurePriceThresholds(wine: Wine) {
        if(!wine.isAlexanderTheGreatWine()){
            wine.price = when {
                (wine.price > 100) -> 100
                (wine.price < 0) -> 0
                else -> wine.price
            }
        }
    }

    private fun Wine.isConservato() = name.contains("Conservato")
    private fun Wine.isEvent() = name.startsWith("Event")
    private fun Wine.isAlexanderTheGreatWine() = name.contains("Wine brewed by Alexander the Great")
    private fun Wine.isEcoBrilliantWine() = name.contains("Eco Brilliant")
    private fun Wine.isCellarOrAgingWine() = isConservato().or(isEvent())
}