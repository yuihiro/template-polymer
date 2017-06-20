package unet.anyclick.aus.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import unet.anyclick.aus.repository.mapper.AdminLogMapper;
import unet.anyclick.aus.util.QueryUtil;

@SuppressWarnings("unchecked")
@Repository
public class AdminLogRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getAdminLogListCnt(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "time", query);
		String sql = "SELECT COUNT(*) FROM log_tbl" + query;
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public List getAdminLogList(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "time", query);
		query += QueryUtil.getOrderQuery($param);
		query += QueryUtil.getLimitQuery($param);
		String sql = "SELECT * FROM log_tbl" + query;
		List result = template.query(sql, $param, new AdminLogMapper());
		return result;
	}

}
