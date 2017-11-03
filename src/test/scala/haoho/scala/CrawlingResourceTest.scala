package crwlr.api.rest.crwlr

import haoho.scala.TestBase
import org.testng.annotations.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
  * Created by haho on 19/10/2017.
  */
class CrawlingResourceTest extends TestBase {
  @Test
  @throws[Exception]
  def testCrawlingData(): Unit = {
    mockMvc.perform(get("/svc/crawler/crawlingData").param //            .param("link", "https://www.lazada.sg/eggtually/")
      ("numberOfProductsCrawled", "2")).andExpect(status.is(200))
  }

  @Test
  @throws[Exception]
  def testGetCrawledData(): Unit = {
    mockMvc.perform(get("/svc/crawler/crawledData")).andExpect(status.is(200))
  }

  @Test
  @throws[Exception]
  def testGetAllVendors(): Unit = {
    mockMvc.perform(get("/svc/crawler/vendors")).andExpect(status.is(200))
  }
}