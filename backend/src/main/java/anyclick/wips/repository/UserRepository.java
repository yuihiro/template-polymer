package anyclick.wips.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import anyclick.wips.repository.mapper.UserMapper;
import anyclick.wips.util.QueryUtil;

@SuppressWarnings("unchecked")
@Repository
public class UserRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public Integer updateUserInfo(Map<String, Object> $param) {
		List<String> except = new ArrayList();
		except.add("dept_id");
		except.add("nas_group_id");

		String sql = "";
		String query = "";
		if ($param.get("id") == null) {
			query = QueryUtil.getUpdateQuery($param, except);
			sql = "UPDATE user_tbl SET " + query + " WHERE user_id = :id";
		} else {
			except.add("id");
			query = QueryUtil.getInsertQuery($param, except);
			sql = "INSERT INTO user_tbl " + query;
		}

		return template.update(sql, $param);
	}

	public List getUserListByInfo(Map<String, Object> $param) {
		String sql = "SELECT * FROM user_tbl WHERE user_id = :id or name = :name";
		List result = template.query(sql, $param, new UserMapper("TINY"));
		return result;
	}

	public List getUserListByDept(Map<String, Object> $param) {
		String sql = "SELECT * FROM (SELECT user_id FROM dept_user_tbl WHERE dept_id = :dept_id) as dept_user_tbl " + "LEFT JOIN (SELECT name, user_id FROM user_tbl) as user_tbl "
				+ "ON dept_user_tbl.user_id = user_tbl.user_id";
		List result = template.query(sql, $param, new UserMapper("TINY"));
		return result;
	}

	public Long getUserListCnt(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		String sql = "SELECT COUNT(*) FROM " + "(SELECT *, if(isnull(OnlineFlag), 0, 1) as active FROM user_tbl "
				+ "LEFT JOIN (SELECT type_code, caption FROM user_type_config_tbl) as user_type_config_tbl ON user_tbl.type = user_type_config_tbl.type_code "
				+ "LEFT JOIN (SELECT user_id as uid, dept_id as did FROM dept_user_tbl) as dept_user_tbl ON user_tbl.user_id = dept_user_tbl.uid "
				+ "LEFT JOIN (SELECT dept_id, dept_name FROM dept_tbl) as dept_tbl ON dept_tbl.dept_id = dept_user_tbl.did "
				+ "LEFT JOIN (SELECT DISTINCT UserName, OnlineFlag FROM radonline where OnlineFlag = 'Y') as radonline ON radonline.UserName = user_tbl.user_id) as woo " + query;
		Long result = template.queryForObject(sql, $param, Long.class);
		return result;
	}

	public List getUserList(Map<String, Object> $param) {
		String query = QueryUtil.getWhereQuery($param);
		query += QueryUtil.getOrderQuery($param);
		query += QueryUtil.getLimitQuery($param);
		String sql = "SELECT * FROM " + "(SELECT *, if(isnull(OnlineFlag), 0, 1) as active FROM user_tbl "
				+ "LEFT JOIN (SELECT type_code, caption FROM user_type_config_tbl) as user_type_config_tbl ON user_tbl.type = user_type_config_tbl.type_code "
				+ "LEFT JOIN (SELECT user_id as uid, dept_id as did FROM dept_user_tbl) as dept_user_tbl ON user_tbl.user_id = dept_user_tbl.uid "
				+ "LEFT JOIN (SELECT dept_id, dept_name FROM dept_tbl) as dept_tbl ON dept_tbl.dept_id = dept_user_tbl.did "
				+ "LEFT JOIN (SELECT DISTINCT UserName, OnlineFlag FROM radonline where OnlineFlag = 'Y' ) as radonline ON radonline.UserName = user_tbl.user_id) as woo " + query;
		List result = template.query(sql, $param, new UserMapper("LIST"));
		return result;
	}

	public Map getUserInfo(String $id) {
		String sql = "SELECT *, if(isnull(OnlineFlag), 0, 1) as active FROM (SELECT * FROM user_tbl WHERE user_id = :id) as user_tbl "
				+ "LEFT JOIN (SELECT type_code, caption FROM user_type_config_tbl) as user_type_config_tbl ON user_tbl.type = user_type_config_tbl.type_code "
				+ "LEFT JOIN (SELECT user_id as uid, dept_id as did FROM dept_user_tbl) as dept_user_tbl ON user_tbl.user_id = dept_user_tbl.uid "
				+ "LEFT JOIN (SELECT dept_id, dept_name FROM dept_tbl) as dept_tbl ON dept_tbl.dept_id = dept_user_tbl.did "
				+ "LEFT JOIN (select DISTINCT UserName, OnlineFlag from radonline where OnlineFlag = 'Y' GROUP BY UserName) as radonline ON radonline.UserName = user_tbl.user_id "
				+ "LEFT JOIN org_user_tbl ON user_tbl.user_id = org_user_tbl.user_id "
				+ "LEFT JOIN (select org_id, name as org_name from org_tbl) as org_tbl ON org_user_tbl.org_id = org_tbl.org_id ";

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", $id);
		Map result = null;
		try {
			result = (Map) template.queryForObject(sql, param, new UserMapper("INFO"));
		} catch (EmptyResultDataAccessException e) {

		}
		return result;
	}

}
