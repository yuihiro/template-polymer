package unet.anyclick.aus.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class NasRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Long getNasListCnt(Map<String, Object> $param) {
		String sql = "SELECT COUNT(*) FROM system_tbl";
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}
}
