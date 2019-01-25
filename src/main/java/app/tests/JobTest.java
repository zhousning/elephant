package app.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JobTest {

	@Test
	public void Tool() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("key1", "v1");
		map.put("key2", "v2");
		map.put("key3", "v3");

		for (Entry<String,String> entry : map.entrySet()) {
            System.out.print(entry.getKey()+":"+entry.getValue()+",");
        }
	}

	@Test
	public void index() {

	}

	@Test
	public void create() {

	}

	@Test
	public void edit() {

	}

	@Test
	public void delete() {

	}
}