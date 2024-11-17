package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WineTest {

    private val currentWineShop = currentWineShopData
    private val futureWineShop = futureWineShopData

    @Test
    fun `Compare new wines values three years in the future`() {
        repeat(3) {
            currentWineShop.next()
        }

        // Loop through currentWineShop and check corresponding futureWineShop values
        currentWineShop.wines.forEach { currentWine ->
            val futureWine = futureWineShop.wines.find { it.name == currentWine.name }
            assertThat(futureWine).isNotNull

            println("Comparing wines:")
            println("Current wine: name=${currentWine.name}, price=${currentWine.price}, expiresInYears=${currentWine.expiresInYears}")
            println("Future wine: name=${futureWine?.name}, price=${futureWine?.price}, expiresInYears=${futureWine?.expiresInYears}")

            assertThat(currentWine.name).isEqualTo(futureWine?.name)
            assertThat(currentWine.price).isEqualTo(futureWine?.price)
            assertThat(currentWine.expiresInYears).isEqualTo(futureWine?.expiresInYears)
        }
    }

    @Test
    fun `Verify price change for expired standard wines`() {
        val currentWineShop = WineShop(listOf(Wine(name = "Standard Wine", price = 4, expiresInYears = 2)))
        val futureWineShop = WineShop(listOf(Wine(name = "Standard Wine", price = 0, expiresInYears = -3)))

        repeat(5) {
            currentWineShop.next()
        }

        val currentWine = currentWineShop.wines[0]
        val futureWine = futureWineShop.wines[0]

        assertThat(currentWine.name).isEqualTo(futureWine.name)
        assertThat(currentWine.price).isEqualTo(futureWine.price)
        assertThat(currentWine.expiresInYears).isEqualTo(futureWine.expiresInYears)
    }

    @Test
    fun `Verify wine prices when going above wine threshold`() {
        val currentWineShop = WineShop(listOf(Wine(name = "Event Wine", price = 98, expiresInYears = 5)))
        val futureWineShop = WineShop(listOf(Wine(name = "Event Wine", price = 100, expiresInYears = 2)))

        repeat(3) {
            currentWineShop.next()
        }

        val currentWine = currentWineShop.wines[0]
        val futureWine = futureWineShop.wines[0]

        assertThat(currentWine.name).isEqualTo(futureWine.name)
        assertThat(currentWine.price).isEqualTo(futureWine.price)
        assertThat(currentWine.expiresInYears).isEqualTo(futureWine.expiresInYears)
    }

    @Test
    fun `Verify price increase for event wine when expireInYears less than 3`() {
        val currentWineShop = WineShop(listOf(Wine(name = "Event Wine", price = 20, expiresInYears = 9)))
        val futureWineShop = WineShop(listOf(Wine(name = "Event Wine", price = 32, expiresInYears = 2)))

        repeat(7) {
            currentWineShop.next()
        }

        val currentWine = currentWineShop.wines[0]
        val futureWine = futureWineShop.wines[0]

        assertThat(currentWine.name).isEqualTo(futureWine.name)
        assertThat(currentWine.price).isEqualTo(futureWine.price)
        assertThat(currentWine.expiresInYears).isEqualTo(futureWine.expiresInYears)
    }

    @Test
    fun `Check that winelist size stays unchanged`() {
        val initialSize = currentWineShop.wines.size

        repeat(5) {
            currentWineShop.next()
        }

        val finalSize = currentWineShop.wines.size

        assertThat(finalSize).isEqualTo(initialSize)
    }

    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `a shop without wine is no shop, is it`() {
        val shop = WineShop(listOf(Wine("name", 0, 0)))

        assertThat(shop).isNotNull

        shop.next()

        assertThat(shop).isNotNull
    }
}