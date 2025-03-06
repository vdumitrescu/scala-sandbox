import scala.concurrent.{ExecutionContext, Future}

object Exercise2 {
  type Source = String

  type Inventory = String
  trait InventoryService {
    def getInventories(source: Source): Future[Seq[Inventory]]
  }

  type TradeId = String
  case class Trade(source: Source, id: TradeId, inventory: Inventory)
  trait TradeService {
    def searchTrades(source: Source, inventories: Seq[Inventory]): Future[Seq[TradeId]]
    def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Future[Seq[Trade]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    inventoryService: InventoryService,
    tradeService: TradeService
  ) {
    // TODO: implement a method that fetches trade data from TradeService for a given Source
    ???
  }
}
