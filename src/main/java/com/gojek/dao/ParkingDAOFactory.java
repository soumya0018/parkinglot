package com.gojek.dao;

import com.gojek.model.ParkingStructure;
import com.gojek.model.Vehicle;

public class ParkingDAOFactory<T extends Vehicle> {

  private static ParkingDAOFactory INSTANCE;

  public static ParkingDAOFactory getInstance() {
    if (INSTANCE == null) {
      synchronized (ParkingDAOFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new ParkingDAOFactory();
        }
      }
    }
    return INSTANCE;
  }

  private ParkingDAOFactory() {
    if (INSTANCE != null) {
      throw new RuntimeException("ERROR: ParkingDAOFactory is already initialized");
    }
  }

  public final ParkingDAO<T> getParkingDAO(final int capacity, final ParkingStructure parkingStructure, final DAOType type) {
    switch (type) {
      case IN_MEMORY:
        return ParkingDAOInMemoryImpl.getInstance(capacity, parkingStructure);
      default:
        throw new RuntimeException("Invalid Type, Error creating Parking DAO");
    }
  }
}