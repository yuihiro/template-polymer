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

import anyclick.wips.repository.AuthLogRepository;

@RequestMapping("api")
@RestController
public class AuthLogController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AuthLogRepository repo;

	@RequestMapping(value = "getAuthLogListCnt", method = RequestMethod.POST)
	public Long getAuthLogListCnt(@RequestParam Map<String, Object> $param) {
		return repo.getAuthLogListCnt($param);
	}

	@RequestMapping(value = "getAuthLogList", method = RequestMethod.POST)
	public List getAuthLogList(@RequestParam Map<String, Object> $param) {
		return repo.getAuthLogList($param);
	}

}
