#!/bin/bash
/root/.sdkman/candidates/java/current/bin/java \
-cp /javademos/demo \
-Xmx3G \
-Dcom.sun.management.jmxremote.port=2222 \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.rmi.port=2222 \
-Djava.rmi.server.hostname=$HOST_HOSTNAME \
org.jconf.demos.$1 $2 $3
