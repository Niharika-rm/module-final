package n.rrs.rest.controller;
import java.sql.SQLException;
import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;


import n.rrs.dao.SettingsDAO;
import n.rrs.exception.AppException;
import n.rrs.model.Settings;



public class SettingsController {
	@Path("/settings")
    @GET
	public List<Settings> findAll() {
	try{
	SettingsDAO dao = new SettingsDAO();
	return dao.fetchAll();
	}catch(SQLException sqe){
		throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	}
	catch(AppException e){
	throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	}
}

		@POST
		@Path("/update")
		public Settings updateSettings(Settings set) {
			SettingsDAO setDao = new SettingsDAO();
			Settings setObject = null;
			try{
				
			setObject = setDao.update(set);
			}
			catch(SQLException sqe){
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
			if(setObject == null){			
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
		
			return set;
		}
}
