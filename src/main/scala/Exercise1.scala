object Exercise1 {

  sealed trait Source
  object Source {
    case object Alpha              extends Source
    case object Beta               extends Source
    case class Other(name: String) extends Source
  }

  type RefKey = String
  final case class BondProduct(
    refKey: RefKey
  )
  final case class SecurityProduct(
    refKey: RefKey
  )

  final case class Product(
    refKey: RefKey,
    bond: Option[BondProduct],
    security: Option[SecurityProduct]
  )

  final case class Transaction(
    source: Source,
    id: String,
    product: Option[Product],
    refKey: Option[RefKey]
  )

  object Transaction {
    // if source is Alpha, return the BondProduct refKey if present, otherwise the Product refKey if present
    // if source is Beta, return the SecurityProduct refKey if present, otherwise the Product refKey if present
    // for any other source, return the Product refKey if present, otherwise the Transaction refKey if present
    def getRefKey(t: Transaction): Option[RefKey] = ???
  }
}
