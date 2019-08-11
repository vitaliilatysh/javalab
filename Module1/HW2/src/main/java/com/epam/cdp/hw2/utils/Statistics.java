package com.epam.cdp.hw2.utils;

import com.epam.cdp.hw2.cacheservice.ICacheService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Statistics {

     private static final Logger logger = Logger.getLogger(Statistics.class);

     private int countEvictions = 0;
     private final List<Long> allTimesToPut = new ArrayList<>();

     public List<Long> getAllPutTimes() {
          return allTimesToPut;
     }

     private int getCountEvictions() {
          return countEvictions;
     }

     public void addEvictionToStats() {
          countEvictions++;
     }

     /**
      * Showing average put time for new objects + total evictions from cache
      * @param cacheService cache service
      */
     public static void showStatistics(ICacheService cacheService) {
          OptionalDouble averagePutTime = cacheService.getStatistics().getAllPutTimes().stream()
                  .mapToDouble(Long::doubleValue)
                  .average();
          if (averagePutTime.isPresent()) {
               logger.info("Average put time: " + averagePutTime.getAsDouble() + " ms");
          }

          logger.info("Total cache evictions: " + cacheService.getStatistics().getCountEvictions());
     }
}
