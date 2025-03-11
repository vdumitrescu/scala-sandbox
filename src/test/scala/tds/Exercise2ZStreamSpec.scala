package tds

import data.Exercise2ZStreamData
import zio._
import zio.test.Assertion._
import zio.test._

object Exercise2ZStreamSpec extends ZIOSpecDefault with Exercise2ZStreamData {
  override def spec: Spec[TestEnvironment with Scope, Any] = suite("getTrades")(
    test("return failure if inventoryService throws an exception") {
      ???
    },
    test("return failure if tradeService throws an exception during search") {
      // TODO: implement the test
      ???
    },
    test("return failure if tradeService throws an exception during get") {
      // TODO: implement the test
      ???
    },
    test("return trades") {
      // TODO: implement the test
      ???
    }
  ).provide(ZLayer.succeed(mockTradeService), ZLayer.succeed(mockInventoryService))
}
