package n.rrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import n.rrs.exception.AppException;
import n.rrs.model.Guest;
import n.rrs.util.DButil;

public class GuestDAO {
public List<Guest> fetchAll() throws SQLException, AppException{
		List<Guest> guestlist = new ArrayList<Guest>();
		
		Connection con = DButil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Guest guest = new Guest();
				guest.setId(rs.getInt("ID"));
				guest.setTableno(rs.getString("TABLENO"));
				guest.setDate(rs.getString("DATE"));
				guest.setTime(rs.getString("TIME"));
				guest.setName(rs.getString("NAME"));
				guest.setPhone(rs.getString("PHONE"));
				guest.setEmail(rs.getString("EMAIL"));
				guest.setPartysize(rs.getInt("PARTYSIZE"));
				guest.setNotes(rs.getString("NOTES"));
				
				guestlist.add(guest);
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
		return guestlist;
	}

public int delete(int guestId) throws SQLException{
	String sql = "delete from reservation where id = ?";
	
	Connection con = DButil.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1,  guestId);
	int response = ps.executeUpdate();
	
	return response;
}
public Guest fetchOne(int guestID) throws SQLException,AppException{
	Guest guest=null;
	
	Connection con = DButil.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		ps = con.prepareStatement("SELECT * FROM reservation where ID=?");
		ps.setInt(1,guestID);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			guest = new Guest();
			guest.setId(rs.getInt("ID"));
			guest.setTableno(rs.getString("TABLENO"));
			guest.setDate(rs.getString("DATE"));
			guest.setTime(rs.getString("TIME"));
			guest.setName(rs.getString("NAME"));
			guest.setPhone(rs.getString("PHONE"));
			guest.setEmail(rs.getString("EMAIL"));
			guest.setPartysize(rs.getInt("PARTYSIZE"));
			guest.setNotes(rs.getString("NOTES"));
			}
		//else{
			//throw new AppException("Guest Not found!");
		//}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new AppException(e.getMessage(), e.getCause());
	}finally {

		ps.close();
		rs.close();
		con.close();
		}
	return guest;
}

public Guest update(Guest guest) throws SQLException{
	
	String sql = "update reservation set TABLEN0 = ?, DATE = ?, TIME = ?, NAME = ? ,PHONE=?,  EMAIL = ?, PARTYSIZE = ?, NOTES = ? where id =?";
	Connection connection = DButil.getConnection();
	PreparedStatement ps = connection.prepareStatement(sql);
	
	ps.setString(1,guest.getTableno());
	ps.setString(2,guest.getDate());
    ps.setString(3,guest.getTime());
    ps.setString(4,guest.getName());
    ps.setString(5,guest.getPhone());
    ps.setString(6,guest.getEmail());
    ps.setInt(7,guest.getPartysize());
    ps.setString(8,guest.getNotes());
    ps.setInt(9, guest.getId());
    
    int response = ps.executeUpdate();
    
    if(response == 1) {
    	return guest;
    }
    
    return null;
}
public Guest create(Guest guest) throws SQLException,AppException {
Connection con = DButil.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try{
		ps = con.prepareStatement("INSERT INTO guest(TABLENO,DATE,TIME,NAME,PHONE,EMAIL,PARTYSIZE,NOTES)"+
 "VALUES(?,?,?,?,?,?,?)");
		ps.setString(1,guest.getTableno());
		ps.setString(2,guest.getDate());
        ps.setString(3,guest.getTime());
        ps.setString(4,guest.getName());
        ps.setString(5,guest.getPhone());
        ps.setString(6,guest.getEmail());
        ps.setInt(7,guest.getPartysize());
        ps.setString(8,guest.getNotes());
        int response = ps.executeUpdate();
        
        if(response == 0) {
        	return null;
        }
        rs = ps.getGeneratedKeys();
		

		if (rs.next()) {
			guest.setId(rs.getInt(1));
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage(), e.getCause());
	} finally {
		ps.close();
		rs.close();
		con.close();
	}
return guest;
}
}
