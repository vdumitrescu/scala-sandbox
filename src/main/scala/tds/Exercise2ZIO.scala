package tds

import tds.Model.{Inventory, Source, Trade, TradeId}
import zio._

object Exercise2ZIO {
  trait InventoryService {
    def getInventories(source: Source): Task[Seq[Inventory]]
  }

  trait TradeService {
    def searchTrades(source: Source, inventories: Seq[Inventory]): Task[Seq[TradeId]]
    def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Task[Seq[Trade]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    maxThreads: Int = 2
  ) {
    def fetchTradeData(source: Source): RIO[InventoryService & TradeService, Seq[Trade]] = ???
  }
}
