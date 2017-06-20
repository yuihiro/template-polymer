package unet.anyclick.aus.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import unet.anyclick.aus.repository.mapper.UserMapper;
import unet.anyclick.aus.util.FileUtil;
import unet.anyclick.aus.util.Util;

@SuppressWarnings("unchecked")
@Repository
public class ConfigRepository {

	public static final String MAIN_CFG_FILE = "/etc/raddb/radiusd.conf";
	public static final String LDAP_CFG_FILE = "/etc/raddb/ldap.conf";
	public static final String EAP_CFG_FILE = "/etc/raddb/eap_tls.conf";
	public static final String GENERAL_CFG_FILE = "/etc/raddb/general.conf";
	public static final String SQL_CFG_FILE = "/etc/raddb/sql.conf";

	public static final String SYSLOG_CFG_FILE = "/etc/syslog.conf";
	public static final String LOGMON_CFG_FILE = "/etc/raddb/logmon.conf";

	@Autowired
	NamedParameterJdbcTemplate template;

	public Map getConfig() {
		Map result = getSystemConfig();
		result.put("rad_info", FileUtil.getConfigFile("src/main/resources/radiusd.conf"));
		result.put("logmon_info", FileUtil.getConfigFile("src/main/resources/logmon.conf"));
		result.put("syslog_info", FileUtil.getConfigFile("src/main/resources/syslog.conf", ""));
		result.put("ha_lst", getHaConfig());
		return result;
	}

	private Map getSystemConfig() {
		String sql = "SELECT * FROM env_tbl";
		List<Map<String, Object>> list = (List<Map<String, Object>>) template.queryForList(sql, Util.getEmptyMap());
		Map result = new HashMap();
		for (Map item : list) {
			result.put(item.get("name"), Util.toHyphen(item.get("value")) + "|" + Util.toHyphen(item.get("kname")) + "|" + Util.toHyphen(item.get("seq")));
		}
		return result;
	}

	private List getHaConfig() {
		String sql = "SELECT ha_system_name, ha_system_ip, ha_system_watch_port, ha_system_start_port, ad_cache_auth_running FROM ha_config_tbl";
		List result = template.queryForList(sql, Util.getEmptyMap());
		return result;
	}

	public Map getAdminConfig() {
		String sql = "SELECT * FROM user_tbl WHERE level = 0 LIMIT 1";
		Map result = null;
		try {
			result = (Map) template.queryForObject(sql, Util.getEmptyMap(), new UserMapper("TINY"));
		} catch (EmptyResultDataAccessException e) {

		}
		result.put("permit_ip_lst", getPermitIpList());
		return result;
	}

	private List getPermitIpList() {
		String sql = "SELECT INET_NTOA(from_ip), INET_NTOA(to_ip), enabled, etc FROM webconsole_accessible_ip_tbl";
		List result = template.queryForList(sql, Util.getEmptyMap());
		return result;
	}

}
