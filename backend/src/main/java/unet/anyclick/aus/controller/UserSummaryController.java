package unet.anyclick.aus.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unet.anyclick.aus.repository.UserSummaryRepository;

@RequestMapping("api")
@RestController
public class UserSummaryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserSummaryRepository repo;

	@RequestMapping(value = "getUserSummaryByTime", method = RequestMethod.POST)
	public Map getUserSummaryByTime(@RequestParam Map<String, Object> $param) {
		return repo.getUserSummaryByTime($param);
	}

}
