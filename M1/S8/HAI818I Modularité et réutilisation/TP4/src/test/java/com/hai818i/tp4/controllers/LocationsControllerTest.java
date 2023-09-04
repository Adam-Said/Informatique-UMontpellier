package com.hai818i.tp4.controllers;


import com.hai818i.tp4.models.Location;
import com.hai818i.tp4.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationsControllerTest {
    @Test
    public void testGetSetLocationId() {
        Location location = new Location();
        long id = 1L;
        location.setLocation_id(id);
        assertEquals(id, location.getLocation_id());
    }

    @Test
    public void testGetSetUsers() {
        Location location = new Location();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        users.add(user1);
        users.add(user2);
        location.setUsers(users);
        assertEquals(users, location.getUsers());
    }

    @Test
    public void testGetSetLatitude() {
        Location location = new Location();
        float latitude = 1.23f;
        location.setLatitude(latitude);
        assertEquals(latitude, location.getLatitude());
    }

    @Test
    public void testGetSetLongitude() {
        Location location = new Location();
        float longitude = 4.56f;
        location.setLongitude(longitude);
        assertEquals(longitude, location.getLongitude());
    }

    @Test
    public void testGetSetLocationDate() {
        Location location = new Location();
        Date locationDate = new Date();
        location.setLocation_date(locationDate);
        assertEquals(locationDate, location.getLocation_date());
    }

}