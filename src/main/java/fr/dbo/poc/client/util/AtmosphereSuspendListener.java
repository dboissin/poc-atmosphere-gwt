package fr.dbo.poc.client.util;

public interface AtmosphereSuspendListener {

    void onMessage(String message);
    
    void onError(String error, int status);
    
}
