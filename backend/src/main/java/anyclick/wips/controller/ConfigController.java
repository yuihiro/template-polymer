package anyclick.wips.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import anyclick.wips.repository.ConfigRepository;

@RestController
@RequestMapping("api")
public class ConfigController {

	@Autowired
	ConfigRepository repo;

	@RequestMapping(value = "getConfig", method = RequestMethod.POST)
	public Map getConfig() {
		return repo.getConfig();
	}

	@RequestMapping(value = "getAdminConfig", method = RequestMethod.POST)
	public Map getAdminConfig() {
		return repo.getAdminConfig();
	}

}
