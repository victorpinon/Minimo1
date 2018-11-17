package edu.upc.dsa.services;

import edu.upc.dsa.MyBike;
import edu.upc.dsa.MyBikeImpl;
import edu.upc.dsa.Bike;
import edu.upc.dsa.Station;
import edu.upc.dsa.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/bikes", description = "Endpoint to Bike Service")
@Path("/bikes")

public class BikeService {
    //  http://localhost:8080/dsaApp/swagger.json
    //  http://localhost:8080/swagger/

    private MyBike mb;

    public BikeService() {
        this.mb = MyBikeImpl.getInstance();
        this.mb.addUser("user1", "Juan", "Lopex");

        this.mb.addStation("Station1","description:: station1", 10, 3, 3);
        this.mb.addStation("Station2","description:: station2", 10, 3, 3);

        try{
            this.mb.addBike("bike101", "descripton", 25.45, "Station1");
            this.mb.addBike("bike102", "descripton", 70.3, "Station1");
            this.mb.addBike("bike103", "descripton", 10.2, "Station1");

            this.mb.addBike("bike201", "descripton", 1325.45, "Station2");
            this.mb.addBike("bike202", "descripton", 74430.3, "Station2");
            this.mb.addBike("bike203", "descripton", 1320.2, "Station2");
        } catch (Exception e){
        }

    }

    // addUser                      *************************************************************************
    @POST
    @ApiOperation(value = "create a new user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addUser/{idUser}/{name}/{surname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("idUser") String idUser,@PathParam("name") String name, @PathParam("surname") String surname) {
        this.mb.addUser(idUser,name,surname);
        return Response.status(201).build();
    }

    // addStation                   *************************************************************************
    @POST
    @ApiOperation(value = "create a new station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addStation/{idStation}/{description}/{max}/{lat}/{lon}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(@PathParam("idStation") String idStation,@PathParam("description") String description, @PathParam("max") int max, @PathParam("lat") int lat, @PathParam("lon") int lon) {
        this.mb.addStation(idStation, description, max, lat, lon);
        return Response.status(201).build();
    }

    // addBike                      *************************************************************************
    @POST
    @ApiOperation(value = "create a new bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Station not found or full")
    })
    @Path("/addBike/{idBike}/{description}/{kms}/{idStation}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBike(@PathParam("idBike") String idBike,@PathParam("description") String description, @PathParam("kms") double kms, @PathParam("idStation") String idStation) {
        try{
            this.mb.addBike(idStation, description, kms, idStation);
            return Response.status(201).build();
        } catch (Exception e){
            return Response.status(404).build();
        }

    }

    // bikesByStationOrderByKms     *************************************************************************
    @GET
    @ApiOperation(value = "get bikes by station ordered by kms", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/bikesByStationOrderByKms/{idStation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByStationOrderByKms(@PathParam("idStation") String idStation) {
        try{
            List<Bike> x = this.mb.bikesByStationOrderByKms(idStation);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(x) {};
            return Response.status(201).entity(entity).build();
        } catch(Exception e){
            return Response.status(404).build();
        }
    }

    //getBike                       *************************************************************************
    @GET
    @ApiOperation(value = "get a Bike", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Station or User not found")
    })
    @Path("/getBike/{idStation}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBike(@PathParam("idStation") String idStation,@PathParam("userId") String userId) {
        try{
            Bike x = this.mb.getBike(idStation,userId);
            return Response.status(201).entity(x).build();
        } catch(Exception e){
            return Response.status(404).build();
        }
    }

    //bikesByUser                   *************************************************************************
    @GET
    @ApiOperation(value = "get bikes by user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Useer not found")
    })
    @Path("/bikesByUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bikesByUser(@PathParam("userId") String userId) {
        try{
            List<Bike> x = this.mb.bikesByUser(userId);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(x) {};
            return Response.status(201).entity(entity).build();
        } catch(Exception e){
            return Response.status(404).build();
        }
    }
}