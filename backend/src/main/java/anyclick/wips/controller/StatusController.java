package anyclick.wips.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import anyclick.wips.repository.StatusRepository;
import anyclick.wips.util.DateUtil;

@Controller
@RequestMapping("api")
public class StatusController {

	@Autowired
	StatusRepository repo;

	@RequestMapping("getStatusData")
	@ResponseBody
	public Map getStatusData() {
		long user_cnt = repo.getUserCnt(null);
		long nas_cnt = repo.getNasCnt(null);
		long user_request_cnt = repo.getUserRequestCnt(null);
		long auth_success_cnt = getAuthCnt(1);
		long auth_fail_cnt = getAuthCnt(0);
		long user_online_cnt = getOnlieUserCnt();

		Map result = new HashMap();
		result.put("user_cnt", user_cnt);
		result.put("nas_cnt", nas_cnt);
		result.put("user_online_cnt", user_online_cnt);
		result.put("auth_success_cnt", auth_success_cnt);
		result.put("auth_fail_cnt", auth_fail_cnt);
		result.put("user_request_cnt", user_request_cnt);
		return result;
	}

	public Long getAuthCnt(int success) {
		Map param = DateUtil.getTodayTime();
		param.put("success", success);
		return repo.getAuthLogCnt(param);
	}

	public Long getOnlieUserCnt() {
		Map param = DateUtil.getTodayTime();
		return repo.getOnlineUserCnt(param);
	}
}
