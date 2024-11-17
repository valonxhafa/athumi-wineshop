package be.athumi

class WineShop(var wines: List<Wine>) {
    fun next() {
        // Wine Shop logic
        for (i in wines.indices) {
            if (!wines[i].isConservato() && !wines[i].isEvent()) {
                if (wines[i].price > 0) {
                    if (!wines[i].isAlexanderTheGreatWine()) {
                        wines[i].price = wines[i].price - 1
                    }
                }
            } else {
                if (wines[i].price < 100) {
                    wines[i].price = wines[i].price + 1

                    if (wines[i].isEvent()) {
                        if (wines[i].expiresInYears < 8) {
                            if (wines[i].price < 100) {
                                wines[i].price = wines[i].price + 1
                            }
                        }

                        if (wines[i].expiresInYears < 3) {
                            if (wines[i].price < 100) {
                                wines[i].price = wines[i].price + 2
                            }
                        }
                    }
                }
            }

            if (!wines[i].isAlexanderTheGreatWine()) {
                wines[i].expiresInYears = wines[i].expiresInYears - 1
            } else if (wines[i].price < 0) {
                wines[i].price = 0
            }

            if (wines[i].expiresInYears < 0) {
                if (!wines[i].isConservato()) {
                    if (!wines[i].isEvent()) {
                        if (wines[i].price > 0) {
                            if (!wines[i].isAlexanderTheGreatWine()) {
                                wines[i].price = wines[i].price - 1
                            }
                        }
                    } else {
                        wines[i].price = wines[i].price - wines[i].price
                    }
                } else {
                    if (wines[i].price < 100) {
                        wines[i].price = wines[i].price + 1
                    }
                }
            }

            if (wines[i].price < 0) {
                wines[i].price = 0
            }
        }
    }

    private fun Wine.isConservato(): Boolean {
        return name == "Bourdeaux Conservato" || name == "Bourgogne Conservato"
    }

    private fun Wine.isEvent(): Boolean {
        return name.startsWith("Event")
    }

    private fun Wine.isAlexanderTheGreatWine(): Boolean {
        return name == "Wine brewed by Alexander the Great"
    }
}