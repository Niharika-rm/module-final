package n.rrs.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import n.rrs.dao.GuestDAO;
import n.rrs.exception.AppException;
import n.rrs.model.Guest;

@Path("/guests")
@Api(tags={"/guests"})
public class guestController {
	@GET()

	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="See All Reservations")
	@ApiResponses(value= {
    		@ApiResponse(code=200 , message="ok"),
    		@ApiResponse(code=500 , message="Internal Server Error")
    })
	public List<Guest> findAll() {
		try{
		GuestDAO dao = new GuestDAO();
		return dao.fetchAll();
		}catch(SQLException sqe){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		catch(AppException e){
		throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Find a specific reservation")
    @ApiResponses(value= {
    		@ApiResponse(code=200 , message="ok"),
    		@ApiResponse(code=500 , message="Internal Server Error")
    })
	public Guest findOne(@PathParam("id") int guestId){
    	try{
    		GuestDAO dao = new GuestDAO();
    		return dao.fetchOne(guestId);
    		}catch(SQLException sqe) {
    			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
    		}catch(AppException e){
    		throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
    		}
    	
}
    @POST()
    @Path("/makeReservation")    
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Add new reservation")
    @ApiResponses(value= {
    		@ApiResponse(code=200 , message="ok"),
    		@ApiResponse(code=500 , message="Internal Server Error")
    })

	public Guest create(Guest guest) {
		try {
			GuestDAO dao = new GuestDAO();
			return dao.create(guest);
		} catch(SQLException sqe) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
    @PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Update reservation")
    @ApiResponses(value= {
    		@ApiResponse(code=200 , message="ok"),
    		@ApiResponse(code=500 , message="Internal Server Error")
    })
	public Guest update(@PathParam("id") int guestId, Guest guest) {
		
		GuestDAO guestDao = new GuestDAO();
		Guest guestObject = null;
		try{
		 guestObject = guestDao.fetchOne(guestId);
		 guestObject = guestDao.update(guest);
		}
		catch(SQLException sqe){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		catch(AppException e){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		if(guestObject == null){			
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	
		return guest;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@ApiOperation(value="Delete A reservation")
	public int delete (@PathParam("id") int guestId) {
		GuestDAO guestDao = new GuestDAO();
		int guestIdProcessed = -1;
		try{
			guestIdProcessed = guestDao.delete(guestId);
		}
		catch(SQLException sqe){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		if(guestIdProcessed == -1){			
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return 1;
		
	}
	
}
