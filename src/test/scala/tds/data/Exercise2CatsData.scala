package tds.data

import tds.Exercise2Cats._
import tds.Model._
import cats.effect.IO

trait Exercise2CatsData extends ModelData {

  implicit val mockInventoryService: InventoryService[IO] = new InventoryService[IO] {
    override def getInventories(source: Source): IO[List[Inventory]] =
      if (source == badInventorySource) IO.raiseError(new RuntimeException(inventoryError))
      else IO.pure(inventories.toList)
  }

  implicit val mockTradeService: TradeService[IO] = new TradeService[IO] {
    override def searchTrades(source: Source, inventories: List[Inventory]): IO[List[TradeId]] =
      if (source == badTradeSearchSource) IO.raiseError(new RuntimeException(tradeSearchError))
      else IO.pure(manyTradeIds.toList)

    override def getTrades(source: Source)(tradeIdList: List[TradeId]): IO[List[Trade]] =
      if (source == badTradeGetSource) IO.raiseError(new RuntimeException(tradeGetError))
      else IO.pure(manyTrades.filter(t => tradeIdList.contains(t.id)).toList)
  }

  val ingestion: Ingestion = Ingestion(maxBatch = 10)
}
