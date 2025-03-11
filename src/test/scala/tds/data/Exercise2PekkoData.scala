package tds.data

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.{Source => PekkoSource}
import tds.Exercise2Pekko._
import tds.Model._

import scala.concurrent.Future

trait Exercise2PekkoData extends ModelData {

  val mockInventoryService: InventoryService = new InventoryService {
    override def getInventories(source: Source): Future[PekkoSource[Inventory, NotUsed]] =
      if (source == badInventorySource) Future.failed(new RuntimeException(inventoryError))
      else Future.successful(PekkoSource(inventories))
  }

  val mockTradeService: TradeService = new TradeService {
    override def searchTrades(
      source: Source,
      inventories: PekkoSource[Inventory, NotUsed]
    ): Future[PekkoSource[TradeId, NotUsed]] =
      if (source == badTradeSearchSource) Future.failed(new RuntimeException(tradeSearchError))
      else Future.successful(PekkoSource(manyTradeIds))

    override def getTrades(source: Source)(tradeIdList: Seq[TradeId]): Future[PekkoSource[Trade, NotUsed]] =
      if (source == badTradeGetSource) Future.failed(new RuntimeException(tradeGetError))
      else Future.successful(PekkoSource(manyTrades.filter(t => tradeIdList.contains(t.id))))
  }

  val ingestion: Ingestion = Ingestion(
    maxBatch = 50,
    maxThreads = 1,
    inventoryService = mockInventoryService,
    tradeService = mockTradeService
  )
}
