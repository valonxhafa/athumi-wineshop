package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

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
    fun `Check that wines size stays unchanged when there are no eligible event-wines for removal`() {

        val initialSize = currentWineShop.wines.size

        repeat(2) { currentWineShop.next() } //Lowest expireInYears in dataset is 5, so going down 2 years is safe

        val finalSize = currentWineShop.wines.size

        assertThat(finalSize).isEqualTo(initialSize)
    }

    @Test
    fun `Verify event wines are removed from the wines when reaching expiration 0`() {
        val currentWineShop = WineShop(listOf(
            Wine(name = "Event Wine", price = 20, expiresInYears = 1),
            Wine(name = "Event Wine 2", price = 20, expiresInYears = 3),
            Wine(name = "Event Wine 3", price = 20, expiresInYears = 6),
            Wine(name = "Standard Wine", price = 4, expiresInYears = 2),
            Wine(name = "Bourdeaux Conservato", price = 0, expiresInYears = 2)
        ))

        assertThat(currentWineShop.wines.size).isEqualTo(5)

        repeat(5) { currentWineShop.next() }

        assertThat(currentWineShop.wines.size).isEqualTo(3)
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