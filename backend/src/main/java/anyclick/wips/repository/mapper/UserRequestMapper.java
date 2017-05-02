package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import anyclick.wips.util.DateUtil;

public class UserRequestMapper implements RowMapper {

	public UserRequestMapper() {
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("idx", rs.getLong("req_time"));
		vo.put("type", rs.getString("type"));
		vo.put("type_label", MapperHelper.getUserRequestTypeStr(rs.getString("type")));
		vo.put("user_id", rs.getString("user_id"));
		vo.put("name", rs.getString("name"));
		vo.put("reg_time", DateUtil.decodeEpochTime(rs.getLong("req_time")));
		return vo;
	}
}
