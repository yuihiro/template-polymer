package unet.anyclick.aus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unet.anyclick.aus.config.AppProperties;
import unet.anyclick.aus.config.annotation.AuthCheck;
import unet.anyclick.aus.repository.MainRepository;
import unet.anyclick.aus.repository.UserRepository;
import unet.anyclick.aus.util.CryptoUtil;

@RestController
@RequestMapping("api")
public class MainController {

	@Autowired
	UserRepository user_repo;

	@Autowired
	MainRepository main_repo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@AuthCheck()
	public Map login(@RequestParam("id") String $id, @RequestParam("pwd") String $pwd, HttpServletRequest req) {
		String id = CryptoUtil.decrypt($id, AppProperties.CRYOTO_KEY);
		Map vo = user_repo.getUserInfo(id);
		Map result = new HashMap<String, Object>();
		if (vo != null) {
			if (vo.get("pwd").toString().equals($pwd)) {
				req.getSession().setAttribute("id", id);
				req.getSession().setMaxInactiveInterval(10 * 60);
				result.put("id", id);
				result.put("status", vo.get("status"));
				result.put("level", vo.get("level"));
				result.put("session_id", req.getSession().getId());
				result.put("type", "SUCCESS");

				List<Map> user_type_lst = main_repo.getUserTypeList();
				List<Map> dept_lst = main_repo.getDeptList();
				List<Map> nas_group_lst = main_repo.getNasGroupList();
				result.put("user_type_lst", user_type_lst);
				result.put("dept_lst", dept_lst);
				result.put("nas_group_lst", nas_group_lst);
			} else {
				result.put("type", "FAIL_PWD");
			}
		} else {
			result.put("type", "FAIL");
		}
		return result;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}

	@RequestMapping(value = "getAppData", method = RequestMethod.GET)
	@AuthCheck()
	public Map getAppData() {
		List<Map> user_type_lst = main_repo.getUserTypeList();
		List<Map> dept_lst = main_repo.getDeptList();
		List<Map> nas_group_lst = main_repo.getNasGroupList();
		Map result = new HashMap<>();
		result.put("user_type_lst", user_type_lst);
		result.put("dept_lst", dept_lst);
		result.put("nas_group_lst", nas_group_lst);

		return result;
	}

}
