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

import anyclick.wips.repository.AdminLogRepository;

@RequestMapping("api")
@RestController
public class AdminLogController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AdminLogRepository repo;

	@RequestMapping(value = "getAdminLogListCnt", method = RequestMethod.POST)
	public Long getAdminLogListCnt(@RequestParam Map<String, Object> $param) {
		return repo.getAdminLogListCnt($param);
	}

	@RequestMapping(value = "getAdminLogList", method = RequestMethod.POST)
	public List getAdminLogList(@RequestParam Map<String, Object> $param) {
		return repo.getAdminLogList($param);
	}

}
