package util;

public interface DbProperties {
	   public static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	   
	   String localUrl = "";
	   public static final String URL="jdbc:oracle:thin:@kosta_high?TNS_ADMIN=/"+ localUrl +"/Wallet_kosta";

	   String USER_ID="";
	   String USER_PASS="";
	}
