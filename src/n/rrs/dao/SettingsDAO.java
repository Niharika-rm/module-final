package n.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import n.rrs.exception.AppException;
import n.rrs.model.Settings;
import n.rrs.util.DButil;

public class SettingsDAO {
public List<Settings> fetchAll() throws SQLException, AppException{
		List<Settings> setlist = new ArrayList<Settings>();
		
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Settings set = new Settings();
				set.setName(rs.getString("NAME"));
				set.setAddress(rs.getString("Address"));
				set.setPhone(rs.getString("PHONE"));
				set.setUsername(rs.getString("Username"));
				set.setPassword(rs.getString("Password"));
				set.setAutoAssign(rs.getString("AutoAssign"));
				setlist.add(set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}finally {

			ps.close();
			rs.close();
			con.close();
			}
		return setlist;
	}


public Settings update(Settings set) throws SQLException{
	
	String sql = "update settings set NAME = ?, ADDRESS = ?, PHONE = ?, USERNAME = ? ,PASSWORD=?,  AUTOASSIGN = ?";
	Connection connection = DButil.getConnection();
	PreparedStatement ps = connection.prepareStatement(sql);
	
	ps.setString(1,set.getName());
    ps.setString(2,set.getName());
    ps.setString(3,set.getPhone());
    ps.setString(4,set.getUsername());
    ps.setString(5,set.getPassword());
    ps.setString(6,set.getAutoAssign());
   
    
    int response = ps.executeUpdate();
    
    if(response == 1) {
    	return set;
    }
    
    return null;
}
}

