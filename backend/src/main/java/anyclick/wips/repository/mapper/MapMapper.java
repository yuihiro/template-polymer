package anyclick.wips.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class MapMapper implements RowMapper {

	public Boolean is_byte = false;

	public MapMapper(Boolean $is_byte) {
		is_byte = $is_byte;
	}

	public MapMapper() {
	}

	@Override
	public Map mapRow(ResultSet rs, int row) throws SQLException {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("id", rs.getInt("map_id"));
		vo.put("name", rs.getString("location"));
		vo.put("parent_id", rs.getInt("parent_id"));
		vo.put("depth", rs.getInt("depth"));
		vo.put("office_width", rs.getInt("img_wid_org") * 0.1);
		vo.put("office_height", rs.getInt("img_hei_org") * 0.1);
		vo.put("image_width", rs.getInt("imgwid_scr"));
		vo.put("image_height", rs.getInt("imghei_scr"));
		vo.put("image_ratio", rs.getFloat("ratio_org"));
		vo.put("x_pos", rs.getInt("x_pos"));
		vo.put("y_pos", rs.getInt("y_pos"));
		vo.put("sort_no", rs.getInt("sort_no"));
		vo.put("image_name", rs.getString("img_name"));
		vo.put("image_path", rs.getString("img_path"));
		if (is_byte) {
			vo.put("image_byte", rs.getBytes("img_byte"));
		}
		vo.put("parent_name", rs.getString("parent_name"));
		vo.put("saved_time", rs.getString("reg_time"));
		//vo.put("comment", Util.formatNull(rs.getString("description")));
		vo.put("profile_id", rs.getInt("profile_idx"));
		vo.put("profile_name", rs.getString("name"));
		vo.put("size_str", Math.round(rs.getInt("img_wid_org") * 0.1) + "m | " + Math.round(rs.getInt("img_hei_org") * 0.1) + "m");
		vo.put("pos_str", rs.getInt("x_pos") + " | " + rs.getInt("y_pos"));
		return vo;
	}
}
