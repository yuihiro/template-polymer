package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class UserTypeMapper implements RowMapper {

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("id", rs.getString("type_code"));
		vo.put("name", rs.getString("caption"));
		vo.put("used", rs.getString("used"));
		vo.put("vlan_id", rs.getString("vlan_id"));
		return vo;
	}

}
