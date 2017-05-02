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

import anyclick.wips.repository.UserRepository;

@RequestMapping("api")
@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public UserRepository repo;

	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public Integer updateUserInfo(@RequestParam Map<String, Object> $param) {
		return repo.updateUserInfo($param);
	}

	@RequestMapping(value = "getUserListByInfo", method = RequestMethod.POST)
	public List getUserListByInfo(@RequestParam Map<String, Object> $param) {
		return repo.getUserListByInfo($param);
	}

	@RequestMapping(value = "getUserListCnt", method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public Long getUserCnt(@RequestParam Map<String, Object> $param) {
		return repo.getUserListCnt($param);
	}

	@RequestMapping(value = "getUserList", method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public List getUserList(@RequestParam Map<String, Object> $param) {
		return repo.getUserList($param);
	}

	@RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.OK)
	public Map getUserInfo(@RequestParam("id") String $id) {
		return repo.getUserInfo($id);
	}

}
