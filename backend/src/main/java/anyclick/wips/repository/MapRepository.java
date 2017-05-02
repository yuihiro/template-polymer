package anyclick.wips.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import anyclick.wips.repository.mapper.MapMapper;

@SuppressWarnings("unchecked")
@Repository
public class MapRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public List getMapList() {
		String sql = "SELECT *, parent_map.location as parent_name from map_info_tbl " + "LEFT JOIN event_policy_profile_tbl "
				+ "ON map_info_tbl.profile_idx = event_policy_profile_tbl.idx " + "LEFT JOIN (select map_id, location from map_info_tbl) as parent_map "
				+ "ON map_info_tbl.parent_id = parent_map.map_id " + "ORDER BY depth, sort_no, map_info_tbl.location";
		List result = template.query(sql, new MapMapper(true));
		return result;
	}

	public Map getMapInfo(String $id) {
		String sql = "select * from admin_user_tbl where user_id = :id";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", $id);
		Map result = (Map) template.queryForObject(sql, parameters, new MapMapper());
		return result;
	}
}
