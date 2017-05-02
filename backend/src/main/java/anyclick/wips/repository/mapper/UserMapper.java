package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.DateUtil;
import anyclick.wips.util.Util;

public class UserMapper implements RowMapper {

	public String type = "LIST";

	public UserMapper(String $type) {
		type = $type;
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("id", rs.getString("user_id"));
		vo.put("name", rs.getString("name"));
		vo.put("email", rs.getString("email"));
		if (type == "TINY") {
			return vo;
		}
		vo.put("pwd", rs.getString("pwd"));
		vo.put("status", rs.getString("status"));
		vo.put("level", rs.getString("level"));
		if (type == "LOGIN") {
			return vo;
		}
		vo.put("type", rs.getString("type"));

		vo.put("type_label", rs.getString("caption"));
		vo.put("dept_id", Util.toNullStr(rs.getString("dept_id")));
		vo.put("dept_name", Util.toHyphen(rs.getString("dept_name")));
		vo.put("last_login_time", DateUtil.decodeEpochTime(rs.getLong("last_login")));

		vo.put("status_label", MapperHelper.getUserStatusStr(rs.getString("status")));
		vo.put("level_label", MapperHelper.getUserLevelStr(rs.getString("level")));

		vo.put("active", rs.getInt("active"));

		if (type == "LIST") {
			return vo;
		}

		vo.put("create_time", DateUtil.decodeEpochTime(rs.getLong("when_created")));
		vo.put("change_time", DateUtil.decodeEpochTime(rs.getLong("when_changed")));
		vo.put("last_pwd_time", DateUtil.decodeEpochTime(rs.getLong("last_pwd_change")));
		vo.put("tel", Util.toHyphen(rs.getString("tel")));
		vo.put("mobile", Util.toHyphen(rs.getString("mobile")));
		vo.put("one_x_use", Util.toBoolean(rs.getString("one_x_use")));
		vo.put("simultaneous_use", rs.getInt("simultaneous_use"));
		vo.put("eap_algo", rs.getInt("eap_algo"));
		vo.put("company", Util.toHyphen(rs.getString("company")));
		vo.put("hr_emp_num", Util.toHyphen(rs.getString("hr_emp_num")));
		vo.put("hr_title", Util.toHyphen(rs.getString("hr_title")));
		vo.put("vlan_id", Util.toHyphen(rs.getString("vlan_id")));
		vo.put("hw_id", Util.toHyphen(rs.getString("hw_id")));
		vo.put("auth_resp_ext_attr", Util.toHyphen(rs.getString("auth_resp_ext_attr")));
		vo.put("pwd_life_time", Util.toHyphen(rs.getString("pwd_life_time")));
		vo.put("validity_begin", Util.toHyphen(rs.getString("validity_begin")));
		vo.put("validity_end", Util.toHyphen(rs.getString("validity_end")));

		vo.put("hr_sync", MapperHelper.getHrSyncStr(rs.getString("hr_sync")));
		vo.put("nas_group_id", Util.toHyphen(rs.getString("org_id")));
		vo.put("nas_group_name", Util.toHyphen(rs.getString("org_name")));

		return vo;
	}
}
