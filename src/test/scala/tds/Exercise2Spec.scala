package tds

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import tds.data.Exercise2Data

class Exercise2Spec extends AnyFlatSpec with Matchers with Exercise2Data with ScalaFutures {

  "getTrades" should "return failure if inventoryService throws an exception" in {
    // TODO: implement the test
  }

  it should "return failure if tradeService throws an exception during search" in {
    // TODO: implement the test
  }

  it should "return failure if tradeService throws an exception during get" in {
    // TODO: implement the test
  }

  it should "return trades" in {
    // TODO: implement the test
  }
}
