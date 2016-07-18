package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.Service.impl.LocationServiceImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyController(urlPath = "/locations")
public class LocationController {
    @MyRequestMethod(urlPath = "/all")
    public List<Location> getAllLocations(){
        return new LocationServiceImpl().getAllLocations();
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneLocation(){
        return "oneRandomLocation";
    }
}
