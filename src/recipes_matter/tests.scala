package recipes_matter

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

class FirstSpec extends FlatSpec {
  
  "The search" should "be able to search with a muiltple-part word" in {
    assert(Search.dealWithInput("soft brown sugar") != "fail")
  }
  
}