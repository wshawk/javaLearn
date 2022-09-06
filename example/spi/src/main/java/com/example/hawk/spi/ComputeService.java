package com.example.hawk.spi;

/**
 * @author hawk
 * @package com.example.hawk.spi
 * @desc
 * @date 2022/9/6
 */
public class ComputeService implements SpiService{
    @Override
    public String supplyService() {
        return "compute Service";
    }
}
