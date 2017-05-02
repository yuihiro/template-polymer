package anyclick.wips.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import anyclick.wips.repository.AccessLogRepository;

@RequestMapping("api")
@RestController
public class AccessLogController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccessLogRepository repo;

	@RequestMapping(value = "getAccessLogListCnt", method = RequestMethod.POST)
	public Long getAccessLogListCnt(@RequestParam Map<String, Object> $param) {
		return repo.getAccessLogListCnt($param);
	}

	@RequestMapping(value = "getAccessLogList", method = RequestMethod.POST)
	public List getAccessLogList(@RequestParam Map<String, Object> $param) {
		return repo.getAccessLogList($param);
	}

}
