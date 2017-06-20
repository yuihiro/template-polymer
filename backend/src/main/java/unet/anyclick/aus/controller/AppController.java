package unet.anyclick.aus.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import unet.anyclick.aus.config.AppProperties;

@RequestMapping()
@RestController
public class AppController implements ServletContextAware {

	@Autowired
	private Environment env;

	private ServletContext servletContext;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void setServletContext(ServletContext servletCtx) {
		this.servletContext = servletCtx;
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public Map info() throws IOException {
		Map result = new HashMap<>();
		result.put("name", AppProperties.name);
		result.put("version", AppProperties.version);
		result.put("buildTime", AppProperties.buildTime);
		result.put("debug", AppProperties.debug);
		return result;
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public long test() throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd");

		Map a = new HashMap();
		a.put("label", "aa");
		a.put("date", "2017-03-24");
		List<Map> source = new ArrayList();
		source.add(a);
		a = new HashMap();
		a.put("label", "aa");
		a.put("date", "2017-04-24");
		source.add(a);
		a = new HashMap();
		a.put("label", "bb");
		a.put("date", "2017-02-24");
		source.add(a);

		long result = 0;
		source.stream().mapToLong(it -> {
			try {
				return f.parse(it.get("date").toString()).getTime();
			} catch (Exception e) {
				return 0;
			}
		}).max().getAsLong();
		return result;

		//return DateUtil.getDateFromTwoDate("17-03-24", "17-04-24");
	}

}
