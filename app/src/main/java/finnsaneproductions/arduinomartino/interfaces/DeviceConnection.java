package finnsaneproductions.arduinomartino.interfaces;


import java.io.IOException;

public interface DeviceConnection {
     void requestPermission(Runnable granted);
     void open() throws IOException;
     Double getMeasure() throws IOException;
     void close();

}
