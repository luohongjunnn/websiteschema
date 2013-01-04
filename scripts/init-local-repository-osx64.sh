#!/bin/bash

#  mvn install:install-file
#    -Dfile=<path-to-file>
#    -DgroupId=<group-id>
#    -DartifactId=<artifact-id>
#    -Dversion=<version>
#    -Dpackaging=<packaging>
#    -DgeneratePom=true
#
#  Where: <path-to-file>  the path to the file to load
#         <group-id>      the group that the file should be registered under
#         <artifact-id>   the artifact name for the file
#         <version>       the version of the file
#         <packaging>     the packaging of the file e.g. jar

mvn install:install-file -Dfile=../lib/webrender-swing-6.0-osx64/corecomponents-swing-osx64.jar -DgroupId=com.webrenderer -DartifactId=corecomponents-swing -Dversion=6.0b3 -Dpackaging=jar -DgeneratePom=true -Dclassifier=osx64

mvn install:install-file -Dfile=../lib/webrender-swing-6.0-osx64/webrenderer-swing.jar -DgroupId=com.webrenderer -DartifactId=webrenderer-swing -Dversion=6.0b3 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=../lib/webrender-swing-6.0-osx64/webrenderer-swing-osx64.jar -DgroupId=com.webrenderer -DartifactId=libwebrenderer -Dversion=6.0b3 -Dpackaging=jar -DgeneratePom=true -Dclassifier=osx64

mvn install:install-file -Dfile=../lib/weka-3.6.2.jar -DgroupId=weka -DartifactId=weka -Dversion=3.6.2 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=../lib/lib-gi.jar -DgroupId=lib-gi -DartifactId=lib-gi -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=../lib/eye-ocr-1.0-ALPHA.jar -DgroupId=com.eye-ocr -DartifactId=eye-ocr -Dversion=1.0-ALPHA -Dpackaging=jar -DgeneratePom=true
