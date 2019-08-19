package com.epam.cdp.hw2.cacheservice;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public abstract class AbstractCacheService implements ICacheService{

     private static final Logger logger = Logger.getLogger(AbstractCacheService.class);

     private static int countEvictions = 0;
     private static final List<Long> allTimesToPut = new ArrayList<>();

     List<Long> getAllPutTimes() {
          return allTimesToPut;
     }

     private int getCountEvictions() {
          return countEvictions;
     }

     void addEvictionToStats() {
          countEvictions++;
     }

     /**
      * Showing average put time for new objects + total evictions from cache
      *
      */
     public void showStatistics() {
          OptionalDouble averagePutTime = this.getAllPutTimes().stream()
                  .mapToDouble(Long::doubleValue)
                  .average();
          if (averagePutTime.isPresent()) {
               logger.info("Average put time: " + averagePutTime.getAsDouble() + " ms");
          }

          logger.info("Total cache evictions: " + this.getCountEvictions());
     }
}
