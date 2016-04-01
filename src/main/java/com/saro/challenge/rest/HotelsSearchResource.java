package com.saro.challenge.rest;
import com.saro.challenge.Model.HotelVO;
import com.saro.challenge.dao.HotelDAO;
import com.saro.challenge.util.RateLimitUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sbalakrishnan on 3/30/16.
 */
@Path("/hotel")

public class HotelsSearchResource {

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HotelVO getHotelById(@PathParam("id") int id, @HeaderParam("apikey") String header) {
        HotelVO hotelVO = null;
        HotelDAO hotelDAO = null;
        if (header != null) {

            hotelDAO = new HotelDAO();
            hotelVO = hotelDAO.getHotelById(id);
            System.out.println(hotelVO.toString());


        }

        return  hotelVO;
    }

    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HotelVO> getHotelById(@QueryParam("sortOrderByPrice") @DefaultValue("desc") String sortOrderByPrice,
                                      @HeaderParam("apikey") String header) {


        HotelDAO hotelDAO = null;
        List<HotelVO> hotelVOList = null;
        if (header != null) {


            hotelDAO = new HotelDAO();
            int sortOrder;
            if(sortOrderByPrice != null && sortOrderByPrice.equalsIgnoreCase("asc"))
                sortOrder = 1;
            else
                sortOrder = -1;

            hotelVOList = hotelDAO.getHotelByPrice(sortOrder);

        }
        return  hotelVOList;

    }

}