package Repository;

import Entity.Login;
import Entity.TypeUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository implements Repository<Login> {
    private Connection connection = Singleton.getInstance().getConnection();

    public LoginRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS Login(id serial,username varchar(50) PRIMARY KEY,password varchar(50),kind varchar(50) )";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    @Override
    public void add(Login login) throws SQLException {
        String insert = "";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,login.getUsername());
        preparedStatement.setString(2,login.getPassword());
        preparedStatement.setString(3, String.valueOf(login.getTypeUser()));
        preparedStatement.executeUpdate();
    }

    @Override
    public int find(String input) throws SQLException {
        String find = "SELECT * FROM Login WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        preparedStatement.setString(1,input);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }

}
