package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Tania
 *
 */
@RunWith(SpringRunner.class)

@SpringBootTest

@AutoConfigureMockMvc
public class TimeControllerTest {
 
	
	@Autowired
	private MockMvc mockMvc;

	
	/**
	 * @throws Exception
	 * 
	 */
	@Test
	public void currentTimeShouldReturnTime() throws Exception {
		DateTime date;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		date=formatter.parseDateTime("2018-01-16T00:00:00.000-05:00");
		
		DateTimeUtils.setCurrentMillisFixed(date.getMillis());

		this.mockMvc.perform(get("/time/current")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(("2018-01-16T00:00:00.000-05:00")));
	}

}
