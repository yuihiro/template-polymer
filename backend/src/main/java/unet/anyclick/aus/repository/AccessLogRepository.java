package unet.anyclick.aus.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import unet.anyclick.aus.repository.mapper.AccessLogMapper;
import unet.anyclick.aus.util.QueryUtil;

@SuppressWarnings("unchecked")
@Repository
public class AccessLogRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getAccessLogListCnt(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "AcctStartTime", query);
		String sql = "SELECT COUNT(*) FROM radacct " + "LEFT JOIN user_tbl ON radacct.UserName = user_tbl.user_id "
				+ "LEFT JOIN dept_user_tbl ON user_tbl.user_id = dept_user_tbl.user_id " + "LEFT JOIN dept_tbl ON dept_user_tbl.dept_id = dept_tbl.dept_id " + query;

		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public List getAccessLogList(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "AcctStartTime", query);
		query += QueryUtil.getOrderQuery($param);
		query += QueryUtil.getLimitQuery($param);
		String sql = "SELECT *, TIMESTAMPDIFF(SECOND,AcctStartTime,AcctStopTime) AS time_gap FROM radacct " + "LEFT JOIN user_tbl ON radacct.UserName = user_tbl.user_id "
				+ "LEFT JOIN dept_user_tbl ON user_tbl.user_id = dept_user_tbl.user_id " + "LEFT JOIN dept_tbl ON dept_user_tbl.dept_id = dept_tbl.dept_id " + query;
		List result = template.query(sql, $param, new AccessLogMapper());
		return result;
	}

}
