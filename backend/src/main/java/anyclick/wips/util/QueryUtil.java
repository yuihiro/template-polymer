package anyclick.wips.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	static public String getWhereQuery(Map<String, Object> $param) {
		if ($param == null) {
			return "";
		}
		String sql = "";
		Set<Entry<String, Object>> entries = $param.entrySet();
		int i = 1;
		int total = entries.size();
		boolean first = true;
		for (Entry<String, Object> entry : entries) {
			i++;
			if (Util.isEmpty(entry.getValue())) {
				continue;
			}
			if (entry.getKey().equals("offset") || entry.getKey().equals("limit")) {
				continue;
			}
			if (entry.getKey().equals("start_time") || entry.getKey().equals("end_time") || entry.getKey().equals("time_column") || entry.getKey().equals("time_epoch")) {
				continue;
			}
			if (entry.getKey().equals("order")) {
				continue;
			}
			if (entry.getKey().contains("|")) {
				continue;
			}

			if (sql.length() == 0) {
				sql += " WHERE ";
			} else {
				if (!first) {
					sql += " AND ";
				}
			}
			first = false;
			//if (entry.getKey().equalsIgnoreCase("id") || entry.getKey().equalsIgnoreCase("user_id") || entry.getKey().equalsIgnoreCase("name")
			//	|| entry.getKey().equalsIgnoreCase("user_name")) {

			if (entry.getValue().toString().contains("%")) {
				//entry.setValue("%" + entry.getValue() + "%");
				sql += " " + entry.getKey() + " LIKE :" + entry.getKey();
			} else {
				sql += " " + entry.getKey() + " = :" + entry.getKey();
			}
		}
		sql += getKeyQuery($param, sql);
		return sql;
	}

	static public String getKeyQuery(Map<String, Object> $param, String $sql) {
		if ($param == null) {
			return "";
		}
		String sql = "";
		Set<Entry<String, Object>> entries = $param.entrySet();
		boolean first = true;
		for (Entry<String, Object> entry : entries) {
			if (entry.getKey().contains("|") && Util.isEmpty(entry.getValue()) == false) {
				if (Util.isEmpty($sql)) {
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				sql += " ( ";
				String[] tokens = StringUtils.split(entry.getKey(), "|");
				for (int i = 1; i <= tokens.length; i++) {
					sql += " " + tokens[i - 1] + " LIKE :" + tokens[i - 1];
					if (i != tokens.length) {
						sql += " OR ";
					}
					$param.put(tokens[i - 1], entry.getValue());
				}
				sql += " ) ";
				return sql;
			}
		}
		return sql;
	}

	static public String getTimeQuery(Map<String, Object> $param, String $time_column, String $sql) {
		if ($param == null || $param.get("start_time") == null) {
			return "";
		}
		String sql = "";
		if (Util.isEmpty($sql)) {
			sql += " WHERE ";
		} else {
			sql += " AND ";
		}
		String column = $time_column;
		String start_time = $param.get("start_time").toString();
		String end_time = $param.get("end_time").toString();
		Boolean epoch = start_time.contains("-") ? false : true;

		if (epoch) {
			start_time = DateUtil.encodeEpochTime(start_time, true);
			end_time = DateUtil.encodeEpochTime(end_time, false);
		} else {
			start_time = DateUtil.toSearchDateTime(start_time, true);
			end_time = DateUtil.toSearchDateTime(end_time, false);
		}

		if (start_time != null && end_time != null) {
			sql += " " + column + " BETWEEN '" + start_time + "' AND '" + end_time + "' ";
		} else if (start_time != null) {
			sql += " '" + start_time + "' <= " + column + " ";
		} else {
			sql += " " + column + " <= '" + end_time + "' ";
		}
		return sql;
	}

	static public String getGroupQuery(Map<String, Object> $param) {
		if ($param == null || $param.get("offset") == null || $param.get("limit") == null) {
			return "";
		}
		Integer offset = Integer.parseInt($param.get("offset").toString());
		Integer limit = Integer.parseInt($param.get("limit").toString());
		return " LIMIT " + offset + "," + limit;
	}

	static public String getOrderQuery(Map $param) {
		if ($param == null || $param.get("order") == null) {
			return "";
		}
		return " ORDER BY " + $param.get("order").toString();
	}

	static public String getLimitQuery(Map<String, Object> $param) {
		if ($param == null || $param.get("offset") == null || $param.get("limit") == null) {
			return "";
		}
		Integer offset = Integer.parseInt($param.get("offset").toString());
		Integer limit = Integer.parseInt($param.get("limit").toString());
		return " LIMIT " + offset + "," + limit;
	}

	static public String getInsertQuery(Map<String, Object> $param, List<String> $except) {
		String key_sql = " ( ";
		String value_sql = " ( ";
		String sql = "";

		Set<Entry<String, Object>> entries = $param.entrySet();
		int i = 1;
		int total = entries.size();
		for (Entry<String, Object> entry : entries) {
			i++;
			if (isContain($except, entry.getKey()) == false) {
				key_sql += entry.getKey();
				value_sql += ":" + entry.getKey();
				if (i != total) {
					key_sql += ", ";
					value_sql += ", ";
				} else {
					key_sql += " ) ";
					value_sql += " ) ";
				}

			}
		}
		sql = key_sql + " VALUES " + value_sql;
		return sql;
	}

	static public String getUpdateQuery(Map<String, Object> $param, List<String> $except) {
		String sql = "";
		Set<Entry<String, Object>> entries = $param.entrySet();
		int i = 1;
		int total = entries.size();
		for (Entry<String, Object> entry : entries) {
			i++;
			if (isContain($except, entry.getKey()) == false) {
				sql += entry.getKey() + " = :" + entry.getKey();
				if (i != total) {
					sql += ", ";
				}
			}
		}
		return sql;
	}

	static public String getJoinQuery(Object $column, String $query) {
		return (Util.isEmpty($column) == false) ? $query : "";
	}

	static public Boolean isContain(List<String> $data, String $value) {
		for (String item : $data) {
			if ($value.equals(item)) {
				return true;
			}
		}
		return false;
	}
}
