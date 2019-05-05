package com.wicky.chedam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wicky.chedam.web.PullController;
import com.wicky.chedam.web.vo.Egg;
import com.wicky.chedam.web.vo.Rope;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CheDamApplicationTests {

    @LocalServerPort
    private int port;

	@Autowired
    private PullController controller;

	@Autowired
    private TestRestTemplate restTemplate;

	@Autowired
    public MockMvc mvc;

	@Before
    public void setup(){
        System.out.println("port = " + port);
    }

    private String getDDL() {
        return "create table order_header (" +
                "  order_no int not null," +
                "  order_type int not null," +
                "  cust_name varchar(10) not null," +
                "  ref_no int null," +
                "  ref_type int null," +
                "  entry_datetime datetime not null" +
                ")";
    }
    private Rope createRootData() {
        Egg header = Egg.builder().name("order_header").ddl(getDDL()).conditions(null).build();
        return Rope.builder().curr(header).refs(null).next(null).build();
    }

	@Test
	public void usingController() {
		assertNotNull(controller);

        Rope root = createRootData();
        String success = controller.pull(root);

        assertEquals("success", success);
    }

    @Test
	public void mockHttpRequest() {
		assertNotNull(controller);

        String resp = restTemplate.postForObject("http://localhost:" + port + "/pull", createRootData(), String.class);
        assertEquals("success", resp);
    }

    @Test
    public void mvcTest() throws Exception {
	    mvc.perform(post("/pull").contentType(MediaType.APPLICATION_JSON).content(toJson(createRootData()))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("success")));
    }

    private String toJson(Rope rootData) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(rootData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
