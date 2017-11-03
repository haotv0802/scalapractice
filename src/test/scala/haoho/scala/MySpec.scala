//package haoho.scala
//
//import junit.framework.Test
//import org.specs._
//import org.specs.runner.{ConsoleRunner, JUnit4}
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//
//class MySpecTest extends JUnit4(MySpec)
////class MySpecSuite extends ScalaTestSuite(MySpec)
//object MySpecRunner extends ConsoleRunner(MySpec)
//
//object MySpec extends Specification {
//  "This wonderful system" should {
//    "save the world" in {
//      val list = Nil
//      list must beEmpty
//    }
//  }
//
//  @Test
//  @throws[Exception]
//  def testGetCrawledData(): Unit = {
//    mockMvc.perform(get("/svc/crawler/crawledData")).andExpect(status.is(200))
//  }
//}