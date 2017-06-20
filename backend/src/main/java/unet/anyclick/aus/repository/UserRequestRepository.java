package unet.anyclick.aus.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import unet.anyclick.aus.repository.mapper.UserRequestMapper;
import unet.anyclick.aus.util.QueryUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRequestRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getUserRequestListCnt(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		String sql = "SELECT COUNT(*) FROM req_tbl INNER JOIN (SELECT user_id, name FROM user_tbl) as user_tbl ON req_tbl.user_id = user_tbl.user_id" + query;
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public List getUserRequestList(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getLimitQuery($param);
		String sql = "SELECT * FROM req_tbl INNER JOIN (SELECT user_id, name FROM user_tbl) as user_tbl ON req_tbl.user_id = user_tbl.user_id" + query;
		List result = template.query(sql, $param, new UserRequestMapper());
		return result;
	}
}
