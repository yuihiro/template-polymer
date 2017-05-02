package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.DateUtil;

public class AdminLogMapper implements RowMapper {

	public AdminLogMapper() {
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("user_id", rs.getString("user_id"));
		vo.put("action", MapperHelper.getActionStr(rs.getString("action")));
		vo.put("ip", rs.getString("ip"));
		vo.put("comment", rs.getString("msg"));
		vo.put("reg_time", DateUtil.decodeEpochTime(rs.getLong("time")));
		return vo;
	}
}
