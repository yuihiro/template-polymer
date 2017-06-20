package unet.anyclick.aus.data;

public class Enums {

	public enum UserStatus {
		AA("정상"), BB("비정상"), NULL("비정상");

		private final String value;

		UserStatus(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value + "";
		}

	}

	public enum City {
		Seoul, Busan, Daegu, Gwangju, Daejun
	}

	public enum SensorType {

		CONTINUE(100), STOP(200), PAUSE(300);

		private final int value;

		SensorType(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value + "";
		}
	}

	public enum ApType {

		CONTINUE(100), STOP(200), PAUSE(300);

		private final int value;

		ApType(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value + "";
		}
	}
}
