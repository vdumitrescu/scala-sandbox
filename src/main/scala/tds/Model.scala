package tds

object Model {
  type Source    = String
  type Inventory = String
  type TradeId   = String

  case class Trade(source: Source, id: TradeId, inventory: Inventory)
}
