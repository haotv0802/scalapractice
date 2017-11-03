package crwlr.api.rest.crawling;

import crwlr.api.rest.crawling.beans.VendorPresenter;
import crwlr.api.rest.crawling.beans.VendorProductPresenter;
import crwlr.api.rest.crawling.interfaces.ICrawledDataDao;
import crwlr.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Date: 10/20/2017 Time: 9:56 AM
 * The DAO is responsible for querying data including vendors and products crawled from given pages.
 *
 * @author haho
 */
@Repository("crawledDataDao")
public class CrawledDataDao implements ICrawledDataDao {
  private static final Logger LOGGER = LogManager.getLogger(CrawledDataDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public CrawledDataDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);

    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<VendorProductPresenter> getAllVendorProducts() {
    final String sql =
              "SELECT                                    "
            + "	p.name,                                  "
            + "	p.category,                              "
            + "	v.name vendorName,                       "
            + "	v.location,                              "
            + "	v.positive,                              "
            + "	v.negative,                              "
            + "	v.neutral,                               "
            + "	v.link,                                  "
            + "	v.rating,                                "
            + "	v.shipOnTime,                            "
            + "	v.size,                                  "
            + "	v.timeOnLazada                           "
            + "FROM                                      "
            + "	crwlr_products p                         "
            + "		INNER JOIN                             "
            + "	crwlr_vendors v ON p.vendor_name = v.name"
            + "	ORDER BY p.name ASC                      "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      VendorProductPresenter presenter = new VendorProductPresenter();
      presenter.setVendorLink(rs.getString("link"));
      presenter.setVendorLocation(rs.getString("location"));
      presenter.setVendorName(rs.getString("vendorName"));
      presenter.setVendorPositive(rs.getInt("positive"));
      presenter.setVendorNeutral(rs.getInt("neutral"));
      presenter.setVendorNegative(rs.getInt("negative"));
      presenter.setVendorTimeOnLazada(rs.getInt("timeOnLazada"));
      presenter.setVendorRating(rs.getFloat("rating"));
      presenter.setVendorSize(rs.getInt("size"));
      presenter.setVendorShipOnTime(rs.getInt("shipOnTime"));
      presenter.setName(rs.getString("name"));
      presenter.setCategory(rs.getString("category"));
      return presenter;
    });
  }

  @Override
  public List<VendorPresenter> getAllVendors() {
    final String sql =
              "SELECT                                 "
            + "	name, timeOnLazada, shipOnTime, size  "
            + "FROM                                   "
            + "	crwlr_vendors                         "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      VendorPresenter presenter = new VendorPresenter();
      presenter.setName(rs.getString("name"));
      presenter.setTimeOnLazada(rs.getInt("timeOnLazada"));
      presenter.setShipOnTime(rs.getDouble("shipOnTime"));
      presenter.setSize(rs.getInt("size"));
      return presenter;
    });
  }
}
