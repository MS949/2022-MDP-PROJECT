/**
 * @file mqtt_esp8266.c
 * @author hanse (im763741@gmail.com)
 * @version 1.0
 * @date 2022-10-20
 *
 * @copyright Copyright (c) 2022
 *
 */
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <string.h>

const char *ssid = "project_two";
const char *password = "11111111";
const char *mqtt_server = "192.168.0.100";

WiFiClient espClient;
PubSubClient client(espClient);
unsigned long lastMsg = 0;
#define MSG_BUFFER_SIZE (50)
char msg[MSG_BUFFER_SIZE];
char to_arduino_msg[MSG_BUFFER_SIZE];
int value = 0;
int AT_Step = 0;

void setup_wifi()
{
  delay(10);
  // We start by connecting to a WiFi network

  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void callback(char *topic, byte *payload, unsigned int length)
{
  memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
  for (int i = 0; i < length; i++)
  {
    to_arduino_msg[i] = (char)payload[i];
  }

  if (topic == "iot/LED")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("a");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("A");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/curtain")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("b");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("B");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/heating_pad")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("c");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("C");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/door")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("d");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("D");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/door/keypad")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("e");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("E");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/pir")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("f");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("F");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "iot/music")
  {
    if (strcmp(to_arduino_msg, "on") == 0)
    {
      Serial.println("g");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
    else if (strcmp(to_arduino_msg, "off") == 0)
    {
      Serial.println("G");
      memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
    }
  }
  else if (topic == "device/connect")
  {
    Serial.println("P");
    memset(to_arduino_msg, 0, MSG_BUFFER_SIZE);
  }

  if ((char)payload[0] == '1')
  {
    digitalWrite(BUILTIN_LED, LOW); // Turn the LED on (Note that LOW is the voltage level)
  }
  else
  {
    digitalWrite(BUILTIN_LED, HIGH); // Turn the LED off by making the voltage HIGH
  }
}

void reconnect()
{
  // Loop until we're reconnected
  while (!client.connected())
  {
    Serial.print("Attempting MQTT connection...");
    // Create a random client ID
    String clientId = "ESP8266Client-";
    clientId += String(random(0xffff), HEX);
    // Attempt to connect
    if (client.connect(clientId.c_str()))
    {
      Serial.println("connected");

      client.publish("device/connected", "STM32 & ESP8266 ON");
    }
    else
    {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void setup()
{
  pinMode(BUILTIN_LED, OUTPUT); // Initialize the BUILTIN_LED pin as an output
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void loop()
{

  if (!client.connected())
  {
    reconnect();
  }
  client.loop();

  unsigned long now = millis();
  if (now - lastMsg > 2000)
  {
    lastMsg = now;
  }

  String str = Serial.readStringUntil('\n');

  if (str.equals("led ON"))
  {
    client.publish("iot/LED", "led ON");
  }
  else if (str.equals("led OFF"))
  {
    client.publish("iot/LED", "led OFF");
  }

  else if (str.equals("curtain ON"))
  {
    client.publish("iot/curtain", "curtain ON");
  }
  else if (str.equals("curtain OFF"))
  {
    client.publish("iot/curtain", "curtain OFF");
  }

  else if (str.equals("heating_pad ON"))
  {
    client.publish("iot/heating_pad", "heating_pad ON");
  }
  else if (str.equals("heating_pad OFF"))
  {
    client.publish("iot/heating_pad", "heating_pad OFF");
  }

  else if (str.equals("door ON"))
  {
    client.publish("iot/door", "door ON");
  }
  else if (str.equals("door OFF"))
  {
    client.publish("iot/door", "door OFF");
  }

  else if (str.equals("door/keypad ON"))
  {
    client.publish("iot/door/keypad", "door/keypad ON");
  }
  else if (str.equals("door/keypad OFF"))
  {
    client.publish("iot/door/keypad", "door/keypad OFF");
  }

  else if (str.equals("pir ON"))
  {
    client.publish("iot/pir", "pir ON");
  }
  else if (str.equals("pir OFF"))
  {
    client.publish("iot/pir", "pir OFF");
  }

  else if (str.equals("music ON"))
  {
    client.publish("iot/music", "music ON");
  }
  else if (str.equals("pir OFF"))
  {
    client.publish("iot/music", "music OFF");
  }
}
