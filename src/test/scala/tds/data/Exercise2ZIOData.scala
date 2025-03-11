package tds.data

import tds.Exercise2ZIO._
import tds.Model._
import zio._

trait Exercise2ZIOData extends ModelData {

  val mockInventoryService: InventoryService = new InventoryService {
    override def getInventories(source: Source): Task[Seq[Inventory]] =
      if (source == badInventorySource) ZIO.fail(new RuntimeException(inventoryError))
      else ZIO.succeed(inventories)
  }

  val mockTradeService: TradeService = new TradeService {
    override def searchTrades(source: Source, inventories: Seq[Inventory]): Task[Seq[TradeId]] =
      if (source == badTradeSearchSource) ZIO.fail(new RuntimeException(tradeSearchError))
      else ZIO.succeed(manyTradeIds)

    override def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Task[Seq[Trade]] =
      if (source == badTradeGetSource) ZIO.fail(new RuntimeException(tradeGetError))
      else ZIO.succeed(manyTrades.filter(t => tradeIdList.contains(t.id)))
  }

  val ingestion: Ingestion = Ingestion(maxBatch = 50)
}
