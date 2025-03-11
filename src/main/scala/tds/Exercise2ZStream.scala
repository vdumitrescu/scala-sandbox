package tds

import tds.Model.{Inventory, Source, Trade, TradeId}
import zio._
import zio.stream.Stream

object Exercise2ZStream {
  trait InventoryService {
    def getInventories(source: Source): Task[Stream[Throwable, Inventory]]
  }

  trait TradeService {
    def searchTrades(source: Source, inventories: Seq[Inventory]): Task[Stream[Throwable, TradeId]]
    def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Task[Stream[Throwable, Trade]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    maxThreads: Int = 2
  ) {
    def fetchTradeData(source: Source): RIO[InventoryService & TradeService, Stream[Throwable, Trade]] = ???
  }
}
