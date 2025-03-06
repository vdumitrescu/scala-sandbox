import Exercise2.{Ingestion, Inventory, InventoryService, Source, Trade, TradeId, TradeService}
import org.scalacheck.Gen

import scala.concurrent.Future
import scala.util.Random

trait Exercise2Data {
  val badInventorySource   = "BadInventory"
  val badTradeSearchSource = "BadTradeSearch"
  val badTradeGetSource    = "BadTradeGet"
  val goodSource           = "GoodSource"

  private val inventories    = Gen.listOfN(10, Gen.numStr).sample.get
  private val manyTradeIds   = Gen.listOfN(50, Gen.alphaStr).sample.get
  val manyTrades: Seq[Trade] = manyTradeIds.map(id => Trade(goodSource, id, Random.shuffle(inventories).head))

  val inventoryError   = "Failed to fetch inventories!"
  val tradeSearchError = "Failed to search trades!"
  val tradeGetError    = "Failed to get trades!"

  private val mockInventoryService = new InventoryService {
    override def getInventories(source: Source): Future[Seq[Inventory]] =
      if (source == badInventorySource) Future.failed(new RuntimeException(inventoryError))
      else Future.successful(inventories)
  }

  private val mockTradeService = new TradeService {
    override def searchTrades(source: Source, inventories: Seq[Inventory]): Future[Seq[TradeId]] =
      if (source == badTradeSearchSource) Future.failed(new RuntimeException(tradeSearchError))
      else Future.successful(manyTradeIds)

    override def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Future[Seq[Trade]] =
      if (source == badTradeGetSource) Future.failed(new RuntimeException(tradeGetError))
      else Future.successful(manyTrades.filter(t => tradeIdList.contains(t.id)))
  }

  val ingestion: Ingestion = Ingestion(maxBatch = 10, mockInventoryService, mockTradeService)
}
