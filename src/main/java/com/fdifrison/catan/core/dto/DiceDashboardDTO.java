package com.fdifrison.catan.core.dto;

import java.util.HashMap;
import java.util.Map;

public class DiceDashboardDTO {

    private final Map<Integer, Long> diceCountMap;

    {
        diceCountMap = new HashMap<>();
        diceCountMap.put(2, 0L);
        diceCountMap.put(3, 0L);
        diceCountMap.put(4, 0L);
        diceCountMap.put(5, 0L);
        diceCountMap.put(6, 0L);
        diceCountMap.put(7, 0L);
        diceCountMap.put(8, 0L);
        diceCountMap.put(9, 0L);
        diceCountMap.put(10, 0L);
        diceCountMap.put(11, 0L);
        diceCountMap.put(12, 0L);
    }

    public Map<Integer, Long> getDiceCountMap() {
        return diceCountMap;
    }
}
