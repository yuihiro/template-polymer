package unet.anyclick.aus.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import unet.anyclick.aus.util.DateUtil;
import unet.anyclick.aus.util.Util;

public class DeptMapper implements RowMapper {

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("id", rs.getString("dept_id"));
		vo.put("name", rs.getString("dept_name"));
		vo.put("depth", rs.getString("dept_depth"));
		vo.put("parent_id", rs.getString("parent_dept_id"));
		vo.put("create_time", DateUtil.decodeEpochTime(rs.getLong("when_created")));
		vo.put("change_time", DateUtil.decodeEpochTime(rs.getLong("when_changed")));
		vo.put("vlan_id", Util.toHyphen(rs.getString("vlan_id")));
		return vo;
	}
}
