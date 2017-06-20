package unet.anyclick.aus.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import unet.anyclick.aus.repository.UserRepository;

public class LoginService {

	@Autowired
	public UserRepository repository;

	public Map checkLogin(String $id, String $pwd) {
		Map vo = repository.getUserInfo($id);
		if (vo != null) {
			String pwd = vo.get("pwd").toString();
			if (pwd.equals($pwd)) {
				vo.put("login_type", "SUCCESS");
			} else {
				vo.put("login_type", "FAIL_PWD");
			}
		} else {
			vo = new HashMap<String, Object>();
			vo.put("login_type", "FAIL_PWD");
		}
		return vo;
	}

}
