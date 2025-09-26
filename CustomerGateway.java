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

}
