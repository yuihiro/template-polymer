package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.Util;

public class NasGroupMapper implements RowMapper {

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("id", rs.getString("org_id"));
		vo.put("name", rs.getString("name"));
		vo.put("wlan_use", rs.getString("wlan_use"));
		vo.put("mac_check", rs.getString("mac_check"));
		vo.put("vlan_id", Util.toHyphen(rs.getString("vlan_id")));

		vo.put("wlan_use_label", getWlanUseLabel(rs.getString("wlan_use")));
		vo.put("mac_check", getMacCheckLabel(rs.getString("mac_check")));
		return vo;
	}

	public String getWlanUseLabel(String $value) {
		if ($value == "Y") {
			return "사용";
		} else {
			return "-";
		}
	}

	public String getMacCheckLabel(String $value) {
		if ($value == "Y") {
			return "확인";
		} else {
			return "-";
		}
	}
}
