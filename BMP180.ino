#include <SFE_BMP180.h>

SFE_BMP180 pressure;

void setup() {
  
  Serial.begin(9600);

  if (pressure.begin())
    Serial.println("BMP180 init success");
  else
  {
    Serial.println("BMP180 init fail");
    while(1); 
  }

}

void loop() {
  
  char status;
  double T,P,p0,a;

  status = pressure.startTemperature();
  if (status != 0)
  {
    delay(status);

    status = pressure.getTemperature(T);
    if (status != 0)
    {
      Serial.print("temperature: ");
      Serial.print(T,2);
      Serial.println(" deg C");                 

      status = pressure.startPressure(3);
      if (status != 0)
      {       
        delay(status);                         

        status = pressure.getPressure(P,T);
        if (status != 0)
        {         
          Serial.print("pressure: ");
          Serial.print(P,2);
          Serial.println(" mb\n");                                                                    
        }
        else Serial.println("error retrieving pressure measurement");
      }
      else Serial.println("error starting pressure measurement");
    }
    else Serial.println("error retrieving temperature measurement");
  }
  else Serial.println("error starting temperature measurement");

  delay(2000);
}
