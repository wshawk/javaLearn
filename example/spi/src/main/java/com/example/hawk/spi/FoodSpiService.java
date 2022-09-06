package com.example.hawk.spi;

/**
 * @author hawk
 * @package com.example.hawk.spi
 * @desc
 * @date 2022/9/6
 */
public class FoodSpiService implements SpiService {
    @Override
    public String supplyService() {
        return "food Service";
    }
}
