package unet.anyclick.aus.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import unet.anyclick.aus.repository.mapper.AuthLogMapper;
import unet.anyclick.aus.util.QueryUtil;

@SuppressWarnings("unchecked")
@Repository
public class AuthLogRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getAuthLogListCnt(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "TimeStamp", query);
		String sql = "SELECT COUNT(*) FROM radauthlog ";
		sql += QueryUtil.getJoinQuery($param.get("name"), "LEFT JOIN user_tbl ON radauthlog.UserName = user_tbl.user_id ");
		sql += QueryUtil.getJoinQuery($param.get("dept_id"), "LEFT JOIN dept_user_tbl ON radauthlog.UserName = dept_user_tbl.user_id ");
		sql += QueryUtil.getJoinQuery($param.get("dept_id"), "LEFT JOIN dept_tbl ON dept_user_tbl.dept_id = dept_tbl.dept_id ");
		sql += query;

		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public List getAuthLogList(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getTimeQuery($param, "TimeStamp", query);
		query += QueryUtil.getOrderQuery($param);
		query += QueryUtil.getLimitQuery($param);
		System.out.println(query);
		String sql = "SELECT * FROM radauthlog ";
		sql += "LEFT JOIN user_tbl ON radauthlog.UserName = user_tbl.user_id ";
		sql += "LEFT JOIN dept_user_tbl ON user_tbl.user_id = dept_user_tbl.user_id ";
		sql += "LEFT JOIN dept_tbl ON dept_user_tbl.dept_id = dept_tbl.dept_id ";
		sql += query;
		List result = template.query(sql, $param, new AuthLogMapper());
		return result;
	}

}
