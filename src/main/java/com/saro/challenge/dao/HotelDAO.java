package com.saro.challenge.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.saro.challenge.Model.HotelVO;
import com.saro.challenge.util.DBConnectionUtil;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.DBCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbalakrishnan on 3/30/16.
 */
public class HotelDAO {

    public HotelVO getHotelById(int id){

        DB mongoDB = DBConnectionUtil.getConnection();
        final JacksonDBCollection<HotelVO, String> hotels =
                JacksonDBCollection.wrap(mongoDB.getCollection("hotels"),
                        HotelVO.class, String.class);
        HotelVO hotelVO = hotels.findOne(DBQuery.and(DBQuery.is("HOTELID",id)));
        return hotelVO;
    }

    public List<HotelVO> getHotelByPrice(int SortOrder){

        DB mongoDB = DBConnectionUtil.getConnection();
        final JacksonDBCollection<HotelVO, String> hotels =
                JacksonDBCollection.wrap(mongoDB.getCollection("hotels"),
                        HotelVO.class, String.class);
        DBCursor<HotelVO> cursor = hotels.find().sort(new BasicDBObject("PRICE",SortOrder));
        System.out.println("cursor" + cursor);
        List<HotelVO> hotelVOList = new ArrayList();
        if(cursor != null)
           hotelVOList = cursor.toArray();

        return hotelVOList;
    }
}
