package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.DateUtil;

public class AuthLogMapper implements RowMapper {

	public AuthLogMapper() {
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("user_id", rs.getString("user_id"));
		vo.put("name", rs.getString("name"));
		vo.put("dept_id", rs.getString("dept_id"));
		vo.put("result", MapperHelper.getSuccessStr(rs.getInt("Result")));
		vo.put("reg_time", DateUtil.trimDateTime(rs.getString("TimeStamp")));
		vo.put("mac_str", rs.getString("cli_mac"));
		vo.put("nas_ip", rs.getString("nas_id"));
		return vo;
	}
}
