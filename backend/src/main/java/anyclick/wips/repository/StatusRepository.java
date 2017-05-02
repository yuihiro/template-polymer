package anyclick.wips.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class StatusRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getUserCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(*) FROM user_tbl";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public Long getOnlineUserCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(*) FROM radonline";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public Long getNasCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(*) FROM system_tbl";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public Long getAuthLogCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(id) FROM radauthlog WHERE Result = :success AND TimeStamp BETWEEN :start_time AND :end_time";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public Long getUserRequestCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(*) FROM req_tbl";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

}
