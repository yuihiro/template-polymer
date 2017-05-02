package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.DateUtil;

public class AccessLogMapper implements RowMapper {

	public AccessLogMapper() {
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("user_id", rs.getString("user_id"));
		vo.put("name", rs.getString("name"));
		vo.put("dept_id", rs.getString("dept_id"));
		vo.put("mac_str", rs.getString("CallingStationId"));
		vo.put("nas_ip", rs.getString("NASIPAddress"));
		vo.put("user_ip", rs.getString("FramedIPAddress"));
		vo.put("input", rs.getString("AcctInputOctets"));
		vo.put("output", rs.getString("AcctOutputOctets"));
		vo.put("login_time", DateUtil.trimDateTime(rs.getString("AcctStartTime")));
		vo.put("logout_time", DateUtil.trimDateTime(rs.getString("AcctStopTime")));
		vo.put("time_gap", DateUtil.secondToTime(rs.getLong("time_gap")));
		return vo;
	}
}
