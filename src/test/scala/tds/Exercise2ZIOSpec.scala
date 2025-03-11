package tds

import data.Exercise2ZIOData
import zio._
import zio.test.Assertion._
import zio.test._

object Exercise2ZIOSpec extends ZIOSpecDefault with Exercise2ZIOData {
  override def spec: Spec[TestEnvironment with Scope, Any] = suite("getTrades")(
    test("return failure if inventoryService throws an exception") {
      // TODO: implement the test
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
