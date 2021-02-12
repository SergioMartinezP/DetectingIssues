# DetectingIssues

This code was used in this talk https://youtu.be/OdPCW09JUjE

The Docker container used in the third and fouth demos was started with:

`docker run -it -v <AbsolutePathTo>/DetectingIssues/javademos:/javademos -p2222:2222 ubuntu bash`

That will create a container restricted by the amount of resources given to Docker Engine if run in a Mac, if run directly on Linux you may need to add restrictions to the amount of resources that it is allowed to use or can block all CPU cores while running

Visual VM can be obtained from https://visualvm.github.io
To run it you need to supply the JVM it will use:
`bin/visualvm --jdkhome ~/.sdkman/candidates/java/14.0.2-zulu`

Zulu mission control can be obtained from https://www.azul.com/products/zulu-mission-control/
To run it go to the dowloaded folder and if in Mac use
`open Zulu\ Mission\ Control.app`



