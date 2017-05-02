package anyclick.wips.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import anyclick.wips.repository.mapper.DeptMapper;
import anyclick.wips.repository.mapper.NasGroupMapper;
import anyclick.wips.repository.mapper.UserTypeMapper;

@SuppressWarnings("unchecked")
@Repository
public class MainRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	public List getUserTypeList() {
		String sql = "select * from user_type_config_tbl where used = 'Y' order by caption";
		List result = template.query(sql, new UserTypeMapper());
		return result;
	}

	public List getDeptList() {
		String sql = "select * from dept_tbl order by dept_depth, dept_name";
		List result = template.query(sql, new DeptMapper());
		return result;
	}

	public List getNasGroupList() {
		String sql = "select * from org_tbl order by name";
		List result = template.query(sql, new NasGroupMapper());
		return result;
	}
}
