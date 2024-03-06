/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here

package com.example.nxtstayz.service;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.RoomJpaRepository;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelJpaService implements HotelRepository {
    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    public ArrayList<Hotel> getHotels() {
        List<Hotel> hotelsList = hotelJpaRepository.findAll();
        ArrayList<Hotel> hotels = new ArrayList<>(hotelsList);
        return hotels;
    }

    public Hotel getHotelById(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return hotel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Hotel addHotel(Hotel hotel) {
        // Room room = hotel.getRooms();
        // int RoomId = room.getRoomId();

        // try {
        // room = roomJpaRepository.findById(RoomId).get();
        // hotel.setRooms(room);
        // hotelJpaRepository.save(hotel);
        // return hotel;
        // } catch (Exception e) {
        // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // }

        hotelJpaRepository.save(hotel);
        return hotel;
    }

    public Hotel updateHotel(int hotelId, Hotel hotel) {
        try {
            Hotel newHotel = hotelJpaRepository.findById(hotelId).get();
            // if (hotel.getRooms() != null) {
            // // newHotel.setRooms(hotel.getRooms());
            // int RoomId = hotel.getRooms().getRoomId();
            // Room newRoom = roomJpaRepository.findById(RoomId).get();
            // newHotel.setRooms(newRoom);
            // }
            if (hotel.getHotelName() != null) {
                newHotel.setHotelName(hotel.getHotelName());
            }
            if (hotel.getLocation() != null) {
                newHotel.setLocation(hotel.getLocation());
            }
            if (hotel.getRating() != 0) {
                newHotel.setRating(hotel.getRating());
            }
            hotelJpaRepository.save(newHotel);
            return newHotel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteHotel(int hotelId) {
        try {
            // hotelJpaRepository.deleteById(hotelId);
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> roomList = roomJpaRepository.findByHotel(hotel);
            for (Room room : roomList) {
                room.setHotel(null);
            }
            roomJpaRepository.saveAll(roomList);
            hotelJpaRepository.deleteById(hotelId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Room> getHotelRoom(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();

            // List<Room> roomsList = roomJpaRepository.findByHotel(hotel);
            // return roomsList;

            // Room room = hotel.getRooms();
            // return room;

            return roomJpaRepository.findByHotel(hotel);

            // return hotel.getRooms();

            // Room room = roomJpaRepository.findById(roomId).get();
            // Hotel hotel = room.getHotel();
            // return hotel;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // public List<Room> getHotelRoom(int hotelId) {
    // try {
    // Hotel hotel = hotelJpaRepository.findById(hotelId)
    // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    // return hotel.getRooms();
    // } catch (Exception e) {
    // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    // }
    // }

    // public List<Room> getHotelRoom(int hotelId) {
    // try {
    // // Hotel hotel = hotelJpaRepository.findById(hotelId).get();
    // return roomJpaRepository.findByHotelId(hotelId).get();
    // } catch (Exception e) {
    // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    // }
    // }
}
