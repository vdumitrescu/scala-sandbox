package tds

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.scaladsl.Sink
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import tds.data.Exercise2PekkoData

import scala.concurrent.ExecutionContext

class Exercise2PekkoSpec extends AnyFlatSpec with Matchers with Exercise2PekkoData with ScalaFutures {
  implicit lazy val system: ActorSystem = ActorSystem("test")

  "getTrades" should "return failure if inventoryService throws an exception" in {
    whenReady(ingestion.fetchTradeData(badInventorySource).failed) { ex =>
      ex.getMessage shouldBe inventoryError
    }
  }

  it should "return failure if tradeService.searchTrades throws an exception" in {
    whenReady(ingestion.fetchTradeData(badTradeSearchSource).failed) { ex =>
      ex.getMessage shouldBe tradeSearchError
    }
  }

  it should "return failure if tradeService.getTrades throws an exception" in {
    // TODO: implement the test
  }

  it should "return trades if all services succeed" in {
    // TODO: implement the test
  }
}
