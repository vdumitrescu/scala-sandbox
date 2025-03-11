package tds

import org.scalacheck.Gen
import org.scalatest.Assertion
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import tds.Exercise1._
import tds.data.Exercise1Data

class Exercise1Spec extends AnyFlatSpec with ScalaCheckDrivenPropertyChecks with Matchers with Exercise1Data {
  "getRefKey" should "return alphaBondKey for Alpha Bonds" in {
    validate(alphaBondGenerator, Some(alphaBondKey))
  }

  it should "return alphaProductKey for Alpha Securities" in {
    validate(alphaSecurityGenerator, Some(alphaProductKey))
  }

  it should "return None for Alpha Swaps" in {
    validate(alphaSwapGenerator, None)
  }

  it should "return betaSecurityKey for Beta Securities" in {
    validate(betaSecurityGenerator, Some(betaSecurityKey))
  }

  it should "return betaProductKey for Beta Bonds" in {
    validate(betaBondGenerator, Some(betaProductKey))
  }

  it should "return None for Beta Swaps" in {
    validate(betaSwapGenerator, None)
  }

  it should "return gammaProductKey for Gamma Products" in {
    validate(gammaProductGenerator, Some(gammaProductKey))
  }

  it should "return gammaTransactionKey for Gamma transactions with refKey" in {
    validate(gammaTransactionGenerator, Some(gammaTransactionKey))
  }

  it should "return None for Gamma transactions without refKey" in {
    // TODO: need a generator for this test case
    validate(???, None)
  }

  private def validate(gen: Gen[Transaction], expected: Option[String]): Assertion =
    forAll(gen) { transaction =>
      Transaction.getRefKey(transaction) shouldBe expected
    }
}
