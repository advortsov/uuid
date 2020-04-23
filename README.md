# uuid
Distributed sequence generator service

Distributed Sequence Generator that generates 64-bit IDs based on the concepts outlined in the Twitter snowflake service:
https://github.com/twitter/snowflake/tree/snowflake-2010

Uses 64-bit long instead of 128 bit UUID because of performance purposes.

Epoch timestamp in milliseconds precision - 41 bits.
The maximum timestamp that can be represented using 41 bits is 241 - 1, or 2199023255551,
which comes out to be Wednesday, September 7, 2039 3:47:35.551 PM. That gives us 69 years with respect to a custom epoch.
Node ID - 10 bits. This gives us 1024 nodes/machines.
Local counter per machine - 12 bits. The counter’s max value would be 4095.
The remaining 1-bit is the signed bit and it is always set to 0.