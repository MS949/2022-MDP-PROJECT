import mqtt from 'mqtt';

const option = {
  host: '192.168.0.100',
  port: 1883,
  protocol: 'mqtt',
};
const client = mqtt.connect(option);

client.on('connect', () => {
  console.log('connected' + client.connected);
});

client.on('error', (error) => {
  console.log(error);
  process.exit(1);
});

client.subscribe('test/test', 'message', { retain: true, qos: 2 });

client.on('message', (topic, message, packet) => {
  console.log('message is ' + message);
  console.log('topic is ' + topic);
});
