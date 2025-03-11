package tds.data

import org.scalacheck.Gen
import tds.Exercise1._

trait Exercise1Data {

  private val sourceGen = for {
    name   <- Gen.asciiPrintableStr
    source <- Gen.oneOf(Source.Alpha, Source.Beta, Source.Other(name))
  } yield source

  private val refKeyGenerator = Gen.asciiPrintableStr

  private def bondProductGenerator(genRefKey: Gen[RefKey] = refKeyGenerator)     = genRefKey.map(BondProduct.apply)
  private def securityProductGenerator(genRefKey: Gen[RefKey] = refKeyGenerator) = genRefKey.map(SecurityProduct.apply)

  private def productGenerator(
    genRefKey: Gen[RefKey] = refKeyGenerator,
    genBond: Gen[Option[BondProduct]] = Gen.option(bondProductGenerator()),
    genSecurity: Gen[Option[SecurityProduct]] = Gen.option(securityProductGenerator())
  ) = for {
    refKey   <- genRefKey
    bond     <- genBond
    security <- genSecurity
  } yield Product(refKey, bond, security)

  private def transactionGenerator(
    genSource: Gen[Source] = sourceGen,
    genId: Gen[String] = Gen.asciiPrintableStr,
    genProduct: Gen[Option[Product]] = Gen.option(productGenerator()),
    genRefKey: Gen[Option[RefKey]] = Gen.option(refKeyGenerator)
  ) =
    for {
      source  <- genSource
      id      <- genId
      product <- genProduct
      refKey  <- genRefKey
    } yield Transaction(source, id, product, refKey)

  val alphaProductKey  = "alphaProductKey"
  val alphaBondKey     = "alphaBondKey"
  val alphaSecurityKey = "alphaSecurityKey"

  val alphaBondGenerator: Gen[Transaction] =
    transactionGenerator(
      Source.Alpha,
      genProduct = productGenerator(
        genRefKey = alphaProductKey,
        genBond = bondProductGenerator(genRefKey = alphaBondKey).map(Option(_)),
        genSecurity = None
      ).map(Option(_))
    )

  val alphaSecurityGenerator: Gen[Transaction] =
    transactionGenerator(
      Source.Alpha,
      genProduct = productGenerator(
        genRefKey = alphaProductKey,
        genBond = None,
        genSecurity = securityProductGenerator(genRefKey = alphaSecurityKey).map(Option(_))
      ).map(Option(_))
    )

  val alphaSwapGenerator: Gen[Transaction] = transactionGenerator(Source.Alpha, genProduct = None)

  val betaProductKey  = "betaProductKey"
  val betaBondKey     = "betaBondKey"
  val betaSecurityKey = "betaSecurityKey"

  val betaBondGenerator: Gen[Transaction] =
    transactionGenerator(
      Source.Beta,
      genProduct = productGenerator(
        genRefKey = betaProductKey,
        genBond = bondProductGenerator(genRefKey = betaBondKey).map(Option(_)),
        genSecurity = None
      ).map(Option(_))
    )

  val betaSecurityGenerator: Gen[Transaction] =
    transactionGenerator(
      Source.Beta,
      genProduct = productGenerator(
        genRefKey = betaProductKey,
        genBond = None,
        genSecurity = securityProductGenerator(genRefKey = betaSecurityKey).map(Option(_))
      ).map(Option(_))
    )

  val betaSwapGenerator: Gen[Transaction] = transactionGenerator(Source.Beta, genProduct = None)

  val gammaSource: Source = Source.Other("gamma")
  val gammaTransactionKey = "gammaTransactionKey"
  val gammaProductKey     = "gammaProductKey"

  val gammaProductGenerator: Gen[Transaction] =
    transactionGenerator(
      gammaSource,
      genRefKey = Some(gammaTransactionKey),
      genProduct = productGenerator(
        genRefKey = gammaProductKey,
        genBond = None,
        genSecurity = None
      ).map(Option(_))
    )

  val gammaTransactionGenerator: Gen[Transaction] =
    transactionGenerator(
      gammaSource,
      genRefKey = Some(gammaTransactionKey),
      genProduct = None
    )
}
