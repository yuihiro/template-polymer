package unet.anyclick.aus.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unet.anyclick.aus.repository.UserRequestRepository;

@RequestMapping("api")
@RestController
public class UserRequestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRequestRepository repo;

	@RequestMapping(value = "getUserRequestListCnt", method = RequestMethod.POST)
	public Long getUserCnt(@RequestParam Map<String, Object> $param) {
		return repo.getUserRequestListCnt($param);
	}

	@RequestMapping(value = "getUserRequestList", method = RequestMethod.POST)
	public List getUserRequestList(@RequestParam Map<String, Object> $param) {
		return repo.getUserRequestList($param);
	}

}
