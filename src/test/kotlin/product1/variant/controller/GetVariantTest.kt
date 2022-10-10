package product1.variant.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.server.ResponseStatusException
import product1.variant.VariantController
import product1.variant.VariantFixture
import product1.variant.VariantService
import product1.variant.VariantView

@WebFluxTest(controllers = [VariantController::class])
class GetVariantTest(
    private val webTestClient: WebTestClient,
    @MockkBean
    private val variantService: VariantService
) : BehaviorSpec({
    Given("옵션 상품 조회하기") {
        val variant = VariantFixture.createVariant()
        val uri = "/products/${variant.productId}/variants/query"

        When("uri 가 유효하지 않은 경우") {
            clearAllMocks()
            val invalidUri = "/products/invalid/variants/query"

            val result = webTestClient
                .get()
                .uri(invalidUri)
                .exchange()

            Then("expectStatus().isBadRequest") {
                result.expectStatus().isBadRequest
            }
        }

        When("옵션 상품이 없는 경우") {
            clearAllMocks()

            coEvery {
                variantService.getVariantByCode(variant.productId, variant.code)
            } throws ResponseStatusException(HttpStatus.NOT_FOUND)

            val result = webTestClient
                .get()
                .uri { builder ->
                    builder
                        .queryParam("code", variant.code)
                        .queryParam("count", variant.stock)
                        .path(uri)
                        .build()

                }
                .exchange()

            Then("expectStatus().isNotFound") {
                result.expectStatus().isNotFound
            }
        }

        When("옵션 상품 재고가 부족한 경우") {
            clearAllMocks()
            val stock = variant.stock.plus(1)
            coEvery {
                variantService.getVariantByCode(variant.productId, variant.code)
            } returns variant

            val result = webTestClient
                .get()
                .uri { builder ->
                    builder
                        .queryParam("code", variant.code)
                        .queryParam("count", stock)
                        .path(uri)
                        .build()

                }
                .exchange()

            Then("expectStatus().isBadRequest") {
                result.expectStatus().isBadRequest
            }
        }

        When("옵션 상품 조회 성공한 경우") {
            clearAllMocks()
            coEvery {
                variantService.getVariantByCode(variant.productId, variant.code)
            } returns variant

            val result = webTestClient
                .get()
                .uri { builder ->
                    builder
                        .queryParam("code", variant.code)
                        .queryParam("count", variant.stock)
                        .path(uri)
                        .build()

                }
                .exchange()

            Then("expectStatus().isOk") {
                result.expectStatus().isOk
            }
            Then("expectBody<VariantView>()") {
                result.expectBody<VariantView>()
            }
        }
    }
})