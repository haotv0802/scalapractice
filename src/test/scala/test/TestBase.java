package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

import java.io.PrintWriter;
import java.net.UnknownHostException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by haho
 * Date:  10/19/2017 Time: 5:15 PM
 */
@WebAppConfiguration
@ContextConfiguration(
    locations = {
        "/config/spring-mvc.xml"
    })
public abstract class TestBase extends AbstractTestNGSpringContextTests {
  protected final Logger logger = LogManager.getLogger(getClass());

  protected MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @BeforeClass
  public void setup() throws UnknownHostException {
    final PrintWriter printWriter = IoBuilder.forLogger(logger).buildPrintWriter();

    mockMvc =
        MockMvcBuilders
            .webAppContextSetup(wac)
            .alwaysDo(print(printWriter))
            .build();
  }
}