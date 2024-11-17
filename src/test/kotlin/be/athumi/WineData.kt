package be.athumi


val currentWineShopData = WineShop(
    listOf(
        Wine(name = "Standard Wine", price = 20, expiresInYears = 10),
        Wine(name = "Bourdeaux Conservato", price = 0, expiresInYears = 2),
        Wine(name = "Another Standard Wine", price = 7, expiresInYears = 5),
        Wine(name = "Wine brewed by Alexander the Great 1", price = 150, expiresInYears = 0),
        Wine(name = "Wine brewed by Alexander the Great 2", price = 80, expiresInYears = 10),
        Wine(name = "Event Wine 1", price = 20, expiresInYears = 15),
        Wine(name = "Event Wine 2", price = 49, expiresInYears = 10),
        Wine(name = "Event Wine 3", price = 49, expiresInYears = 5),
        Wine(name = "Eco Brilliant Wine", price = 6, expiresInYears = 3)
    )
)

//expected data after 3 years
val futureWineShopData = WineShop(
    listOf(
        Wine(name = "Standard Wine", price = 17, expiresInYears = 7),
        Wine(name = "Bourdeaux Conservato", price = 4, expiresInYears = -1),
        Wine(name = "Another Standard Wine", price = 4, expiresInYears = 2),
        Wine(name = "Wine brewed by Alexander the Great 1", price = 150, expiresInYears = 0),
        Wine(name = "Wine brewed by Alexander the Great 2", price = 80, expiresInYears = 10),
        Wine(name = "Event Wine 1", price = 23, expiresInYears = 12),
        Wine(name = "Event Wine 2", price = 52, expiresInYears = 7),
        Wine(name = "Event Wine 3", price = 55, expiresInYears = 2),
        Wine(name = "Eco Brilliant Wine", price = 0, expiresInYears = 0)
    )
)