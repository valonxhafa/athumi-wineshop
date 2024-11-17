package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WineTest {

    val currentWineShop = currentWineShopData
    val futureWineShop = futureWineShopData

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