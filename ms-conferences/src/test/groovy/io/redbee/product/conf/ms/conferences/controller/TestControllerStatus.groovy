package io.redbee.product.conf.ms.conferences.controller

import org.springframework.test.web.servlet.MockMvc
import spock.lang.*

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * @author Joaco Campero
 *
 * created at 14/10/21
 */
class TestControllerStatus extends Specification {

  MockMvc mockMvc = standaloneSetup(new StatusController()).build()

  def "test controller status"() {
    given:
    def expectedContent = "ok"

    when:
    def res = mockMvc.perform(get("/status")).andReturn().response

    then:
    res.status == 200
    res.contentAsString == expectedContent
  }
}
