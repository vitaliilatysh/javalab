package com.epam.cdp.hw2.utils;

import com.epam.cdp.hw2.cacheservice.ICacheService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Statistics {

     private static final Logger logger = Logger.getLogger(Statistics.class);

     private static int countEvictions = 0;
     private static final List<Long> allTimesToPut = new ArrayList<>();

     public static List<Long> getAllTimesToPut() {
          return allTimesToPut;
     }

     public int getCountEvictions() {
          return countEvictions;
     }

     public void incrementEviction() {
          countEvictions++;
     }

     /**
      * Showing average put time for new objects + total evictions from cache
      * @param cacheService cache service
      */
     public static void showStatistics(ICacheService cacheService) {
          OptionalDouble averagePutTime = Statistics.getAllTimesToPut().stream()
                  .mapToDouble(Long::doubleValue)
                  .average();
          if (averagePutTime.isPresent()) {
               logger.info("Average put time: " + averagePutTime.getAsDouble() + " ms");
          }

          logger.info("Total cache evictions: " + cacheService.getStatistics().getCountEvictions());
     }
}
