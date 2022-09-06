package com.example.hawk.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author hawk
 * @package com.example.hawk.spi
 * @desc
 * @date 2022/9/6
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<SpiService> serviceLoaders = ServiceLoader.load(SpiService.class);

        for (SpiService serviceLoader : serviceLoaders) {
            System.out.println(serviceLoader.supplyService());;
        }
    }
}
