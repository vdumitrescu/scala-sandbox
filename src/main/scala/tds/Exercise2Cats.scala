package tds

import tds.Model._

object Exercise2Cats {
  trait InventoryService[F[_]] {
    def getInventories(source: Source): F[List[Inventory]]
  }

  trait TradeService[F[_]] {
    def searchTrades(source: Source, inventories: List[Inventory]): F[List[TradeId]]
    def getTrades(source: Source)(tradeIdList: List[TradeId]): F[List[Trade]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    maxThreads: Int = 2
  ) {

    /**
     * Fetch trade data for given source
     *
     * @param source
     *   the source for trade data
     * @param inventoryService
     *   an InventoryService instance
     * @param tradeService
     *   a TradeService instance
     * @return
     *   a list of trades
     */
    // def fetchTradeData...
  }
}
