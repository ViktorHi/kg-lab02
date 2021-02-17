# Graphic Reader (Lab 02)
Symple application provide opportunities to choose folder with file of competitive formats and watch metadate.
### Supported formats
* .jpg
* .png
* .bmp
* .tif
* .gif
* .pscx

Test folder results:
![](https://github.com/ViktorHi/kg-lab02/blob/master/doc/img/test.png)
Duration test result (6GB folder):
![](https://github.com/ViktorHi/kg-lab02/blob/master/doc/img/duration.png)
# Build 
To build application from sources you should use *Maven* 

1. go to root folder of **graphic-reader** project and run: 
    * mvn compile
    * mvn javafx:run

# Artifacts
### graphic-reader.rar [link](https://github.com/ViktorHi/kg-lab02/tree/master/artifact/win)
Artifact for windows user
To run it you should unzip graphic-reader.rar and open graphic-reader.exe

### graphic-reader.jar [link](https://github.com/ViktorHi/kg-lab02/tree/master/artifact/crossplatform)
Crosplatform artifact to run this java 15 should be installed
* You can use double click, if all java configurations installed successfully
* Or *java -jar graphic-reader.jar* from command line or terminal 

This jar should work on Linux, Mac and Windows.

# Links
To parse metadata next libraries used:
* [metadata-extractor](https://github.com/drewnoakes/metadata-extractor)
* [org.apache.sanselan](https://commons.apache.org/proper/commons-imaging/javadocs/api-release/org/apache/sanselan/Sanselan.html)

# Documentation 
You can find project documentation here [link](https://github.com/ViktorHi/kg-lab02/tree/master/doc)

