
package util;

public interface DbProperties {
	  public static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	   
	  String localUrl = "/Users/USER/Desktop/sql 관련";
	  public static final String URL="jdbc:oracle:thin:@kosta_high?TNS_ADMIN="+localUrl+"/Wallet_kosta";

	  String USER_ID="admin";
	  String USER_PASS="Dlwjddn@1130";
}





/*
package util;

public interface DbProperties {
	   	public static final String DRIVER_NAME="oracle.jdbc.driver.OracleDriver";
	   
	   	String localUrl = "C:/Users/USER/Desktop/sql 관련";
	   	public static final String URL="jdbc:oracle:thin:@localhost:1521:xe";

	   	String USER_ID="son";
	   	String USER_PASS="TIGER";
}*/