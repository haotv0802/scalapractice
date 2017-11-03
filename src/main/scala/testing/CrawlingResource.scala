package testing

import java.util
import java.util.List

import crwlr.api.rest.crawling.beans.VendorProductPresenter
import crwlr.api.rest.crawling.interfaces.{ICrawledDataService, ICrawlingService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(path = Array("/svc"))
class CrawlingResource(@Autowired val crawlingService: ICrawlingService, @Autowired val crawledDataService: ICrawledDataService) {

  @GetMapping(Array("/crawler/crawledData"))
  def getCrawledData: util.List[VendorProductPresenter] = crawledDataService.getAllVendorProducts

}