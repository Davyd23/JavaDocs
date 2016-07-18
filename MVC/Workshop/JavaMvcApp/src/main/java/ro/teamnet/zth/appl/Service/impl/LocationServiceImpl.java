package ro.teamnet.zth.appl.Service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.Service.LocationService;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService
public class LocationServiceImpl implements LocationService {
    @Override
    public List<Location> getAllLocations() {
        return new LocationDao().getAllLocations();
    }
}
