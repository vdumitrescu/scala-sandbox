package tds.data

import tds.Exercise2._
import tds.Model._

import scala.concurrent.Future

trait Exercise2Data extends ModelData {
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

  val ingestion: Ingestion = Ingestion(maxBatch = 10, maxThreads = 2, mockInventoryService, mockTradeService)
}
