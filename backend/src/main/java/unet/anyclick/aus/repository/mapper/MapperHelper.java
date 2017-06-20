package unet.anyclick.aus.repository.mapper;

public class MapperHelper {

	static public String getUserStatusStr(String value) {
		String label = "정상";
		switch (value) {
		case "A":
			label = "정상";
			break;
		case "W":
			label = "승인대기";
			break;
		case "D":
			label = "정기";
			break;
		case "S":
			label = "정지승인대기";
			break;
		case "L":
			label = "잠김";
			break;
		case "H":
			label = "일시정지";
			break;
		case "I":
			label = "유효기간초과";
			break;
		case "U":
			label = "장기미사용";
			break;
		}
		return label;
	}

	static public String getUserLevelStr(String value) {
		String label = "일반사용자";
		switch (value) {
		case "2":
			label = "일반사용자";
			break;
		case "1":
			label = "서브관리자";
			break;
		case "0":
			label = "최고관리자";
			break;
		}
		return label;
	}

	static public String getHrSyncStr(String value) {
		if (value == null) {
			return "웹콘솔 생성";
		}
		String label = "인사정보 추가";
		switch (value) {
		case "I":
			label = "인사정보 동기화 추가";
			break;
		case "M":
			label = "인사정보 동기화 변경";
			break;
		case "E":
			label = "인사정보 동기화 변경무";
			break;
		case "C":
			label = "웹콘솔 생성";
			break;
		}
		return label;
	}

	static public String getUserRequestTypeStr(String value) {
		String label = "-";
		switch (value) {
		case "UA":
			label = "등록";
			break;
		case "UD":
			label = "정지";
			break;
		case "UQ":
			label = "탈퇴";
			break;
		case "DE":
			label = "기간연장";
			break;
		}
		return label;
	}

	static public String getSuccessStr(int value) {
		String label = "-";
		switch (value) {
		case 1:
			label = "성공";
			break;
		case 0:
			label = "실패";
			break;
		case 2:
			label = "자동로그오프";
			break;
		}
		return label;
	}

	static public String getActionStr(String value) {
		if (value == null)
			return "";
		if (value.equals("K"))
			return "DHCPv6작업";
		if (value.equals("J"))
			return "단말현황";
		if (value.equals("A"))
			return "ACL작업";
		if (value.equals("B"))
			return "다운로드";
		if (value.equals("C"))
			return "파일무결성관리";
		if (value.equals("D"))
			return "DHCP작업";
		if (value.equals("E"))
			return "Controller관리";
		if (value.equals("F"))
			return "IP그룹 관리";
		if (value.equals("G"))
			return "Agent";
		if (value.equals("H"))
			return "부서관리";
		if (value.equals("I"))
			return "IP관리";
		if (value.equals("M"))
			return "ADMIN작업";
		if (value.equals("N"))
			return "NAC작업";
		if (value.equals("O"))
			return "NAS그룹";
		if (value.equals("P"))
			return "승인관리";
		if (value.equals("R"))
			return "승인요청";
		if (value.equals("S"))
			return "NAS관리";
		if (value.equals("T"))
			return "기타작업";
		if (value.equals("U"))
			return "계정관리";
		if (value.equals("Z"))
			return "관리작업";

		return "";
	}
}
