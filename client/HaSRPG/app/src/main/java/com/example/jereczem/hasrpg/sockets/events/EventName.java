package com.example.jereczem.hasrpg.sockets.events;

/**
 * Created by jereczem on 10.11.15.
 */
public
enum EventName{
    CONNECTION,
    DISCONNECTION,
    NONE;

    public static EventName getEventName(String name){
        for(EventName event : EventName.values()){
            if(name.equals(event.name()))
                return event;
        }
        return NONE;
    }
}
