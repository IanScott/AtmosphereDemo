# AtmosphereDemo
An Atmosphere Frameword demo, which uses the framework to create a websocket.

This small Java web application has two endpoints. The first endpoint allows a
browser to connect using websockets. The second endpoint allows a client to connect using
HTTP and POST a message. The message is then send to all the browser that have a
websocket connections.
The POST endpoint does the following:
- Accept POST requests have an “Authentication” header set to the value “Bearer <UUID>”.
- The message looks like the following:
 {
 “push_message”: “<message text>”,
 “sender_id”: <UUID>
 }
- The sender_id is the UUID from the header.
The browser shows all incomming messages in the format:
<UUID>: <message_text>

