package dal;

public interface DatabaseInfo {
//    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String DBURL="jdbc:sqlserver://TUF-F15-HXNAM\\SQLEXPRESS;databaseName=FreshFoodStore;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
//    public static String USERDB="sa";
//    public static String PASSDB="123";
//}

//Nam
    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String DBURL="jdbc:sqlserver://TUF-F15-HXNAM\\SQLEXPRESS;databaseName=FreshFoodStore;encrypt=false;trustServerCertificate=false;loginTimeout=30"; 
    public static String DBURL="jdbc:sqlserver://freshfoodstore.database.windows.net:1433;database=FreshFoodStore;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"; 
    public static String USERDB="freshfoodstore";
    public static String PASSDB="Free2play2004";

//Trung
//    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String DBURL="jdbc:sqlserver://DESKTOP-5UUDQPS;databaseName= FreshFoodStoreV2;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
//    public static String USERDB="sa";
//    public static String PASSDB="123";
}