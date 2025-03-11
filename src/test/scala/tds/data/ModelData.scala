package tds.data

import org.scalacheck.Gen
import scala.util.Random
import tds.Model._

trait ModelData {
  val badInventorySource   = "BadInventory"
  val badTradeSearchSource = "BadTradeSearch"
  val badTradeGetSource    = "BadTradeGet"
  val goodSource           = "GoodSource"

  val inventories: Seq[Inventory] = Gen.listOfN(10, Gen.numStr).sample.get
  val manyTradeIds: Seq[TradeId]  = Gen.listOfN(50, Gen.alphaStr).sample.get
  val manyTrades: Seq[Trade]      = manyTradeIds.map(id => Trade(goodSource, id, Random.shuffle(inventories).head))

  val inventoryError   = "Failed to fetch inventories!"
  val tradeSearchError = "Failed to search trades!"
  val tradeGetError    = "Failed to get trades!"
}
