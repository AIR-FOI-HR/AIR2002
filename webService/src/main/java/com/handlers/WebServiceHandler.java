package com.handlers;

public interface WebServiceHandler {
     void onDataArrived(
            Object result,
            boolean ok,
            long timeStamp);

    }


