package com.test.task.DI;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private List<Service> services;

    public Cache(){
        services = new ArrayList<Service>();
    }

    public Service getService(String serviceName){

        try{
            for (Service service : services) {
                if(service.getName().equalsIgnoreCase(serviceName)){
                    System.out.println("Returning cached  " + serviceName + " object");
                    return service;
                }
            }
        }catch (Exception e){

        }
        return null;
    }

    public void addService(Service newService){
        try{
            boolean exists = false;
            for (Service service : services) {
                if(service.getName().equalsIgnoreCase(newService.getName())){
                    exists = true;
                }
            }
            if(!exists){
                services.add(newService);
            }
        }catch (Exception e){

        }

    }
}
