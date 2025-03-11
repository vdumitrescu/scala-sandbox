package tds.data

import zio._
import zio.stream._
import tds.Model._
import tds.Exercise2ZStream._

trait Exercise2ZStreamData extends ModelData {
  val mockInventoryService: InventoryService = new InventoryService {
    override def getInventories(source: Source): Task[Stream[Throwable, Inventory]] =
      if (source == badInventorySource) ZIO.fail(new RuntimeException(inventoryError))
      else ZIO.succeed(ZStream.fromIterable(inventories))
  }

  val mockTradeService: TradeService = new TradeService {
    override def searchTrades(source: Source, inventories: Seq[Inventory]): Task[Stream[Throwable, TradeId]] =
      if (source == badTradeSearchSource) ZIO.fail(new RuntimeException(tradeSearchError))
      else ZIO.succeed(ZStream.fromIterable(manyTradeIds))

    override def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Task[Stream[Throwable, Trade]] =
      if (source == badTradeGetSource) ZIO.fail(new RuntimeException(tradeGetError))
      else ZIO.succeed(ZStream.fromIterable(manyTrades.filter(t => tradeIdList.contains(t.id))))
  }

  val ingestion: Ingestion = Ingestion(maxBatch = 50)
}
