package tds

import tds.Model.{Inventory, Source, Trade, TradeId}

import scala.concurrent.{ExecutionContext, Future}

object Exercise2 {

  trait InventoryService {
    def getInventories(source: Source): Future[Seq[Inventory]]
  }

  trait TradeService {
    def searchTrades(source: Source, inventories: Seq[Inventory]): Future[Seq[TradeId]]
    def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Future[Seq[Trade]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    maxThreads: Int = 2,
    inventoryService: InventoryService,
    tradeService: TradeService
  ) {
    // TODO: implement a method that fetches trade data from TradeService for a given Source
    def fetchTradeData(source: Source): Future[Seq[Trade]] = ???
  }
}
