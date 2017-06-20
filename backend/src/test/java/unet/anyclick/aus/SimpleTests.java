package unet.anyclick.aus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import unet.anyclick.aus.data.Enums;
import unet.anyclick.aus.data.Enums.UserStatus;
import unet.anyclick.aus.util.CryptoUtil;
import unet.anyclick.aus.util.Util;

@RunWith(SpringRunner.class)
public class SimpleTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//@Test
	public void enumtest() throws Exception {

		String value = "NULL";
		System.out.println(UserStatus.valueOf(value));

		List<Map> a = Util.getListFromEnum(Enums.ApType.values());
		System.out.println(Util.getStringFromObject(a));
		Map<String, Object> enums = new HashMap<>();
		enums.put("ap_type", Enums.ApType.class.getEnumConstants());
		enums.put("sensor_type", Enums.SensorType.class.getEnumConstants());
		String jsonData = new ObjectMapper().writeValueAsString(enums);
		System.out.println(jsonData);
	}

	//@Test
	public void woo() {
		String text = "adminme";
		String key = "ANYCLICK-KO-100001";
		try {
			System.out.println("plan text : " + text);
			System.out.println("key : " + key);
			String result = CryptoUtil.encrypt(text, key);
			System.out.println("encrypt result : " + result);
			result = CryptoUtil.decrypt(result, key);
			System.out.println("decrypt result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
