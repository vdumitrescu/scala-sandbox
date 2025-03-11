package tds

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.scaladsl.{Sink, Source => PekkoSource}
import org.apache.pekko.stream.{ActorAttributes, Supervision}
import tds.Model._

import scala.concurrent.{ExecutionContext, Future}

object Exercise2Pekko {
  trait InventoryService {
    def getInventories(source: Source): Future[PekkoSource[Inventory, NotUsed]]
  }

  trait TradeService {
    def searchTrades(
      source: Source,
      inventories: PekkoSource[Inventory, NotUsed]
    ): Future[PekkoSource[TradeId, NotUsed]]
    def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Future[PekkoSource[Trade, NotUsed]]
  }

  case class Ingestion(
    maxBatch: Int = 100,
    maxThreads: Int = 2,
    inventoryService: InventoryService,
    tradeService: TradeService
  ) {
    def fetchTradeData(source: Source)(implicit as: ActorSystem): Future[PekkoSource[Trade, NotUsed]] = ???
  }
}
