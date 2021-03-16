package sample.dao;


public class SQLOrder


{
    private static String TABLE_NAME = "forsa.order";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(" +
            "    order_list_id SERIAL, " +
            "    order_date date not null, " +
            "    articul varchar(50) not null, " +
            "    price float8 not null, " +
            "    quanty integer not null ," +
            "    phone varchar(20), " +
            "    status varchar (1) not null, " +
            "    form varchar (1) not null, " +
            "    description varchar(200), " +
            "    PRIMARY KEY (order_list_id)" +
            "" +
            ")";


    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public static final String SELECT_BY_DATE = "SELECT * FROM " + TABLE_NAME + " WHERE order_date = ? ORDER BY order_list_id ASC";

    public static final String SELECT = "SELECT * FROM " + TABLE_NAME + " WHERE order_list_id = ?";

    public static final String SEARCH = "SELECT * FROM " + TABLE_NAME + " WHERE articul = ?";



    public static final String UPDATE = "UPDATE " + TABLE_NAME +
            " SET order_date = ?, articul = ?, price = ?, quanty = ?, phone = ?, status = ?, form = ?, description = ? " +
            "WHERE order_list_id = ?";

    public static final String CREATE_ORDER = "INSERT INTO " + TABLE_NAME + "(order_date, articul, price, " +
            "quanty, phone, status, form, description) VALUES(?, ?, ?, ?, ?," +
            "?, ?, ?)";

    public static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE order_list_id = ?";
}
