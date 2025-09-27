import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerGateway {
    int id;
    String name;

    public CustomerGateway(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public void update() {
        DB.execute("UPDATE Customers SET name=? WHERE id=?", name, id);

    }


    public void delete() {
        DB.execute("DELETE FROM Customers WHERE id=?", id);
    }

    public static CustomerGateway find(int id) {
        try {
            ResultSet rs = DB.query("SELECT * FROM Customers WHERE id=?", id);
            if (rs != null && rs.next()) {
                return new CustomerGateway(rs.getInt("id"), rs.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
