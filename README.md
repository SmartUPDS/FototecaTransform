# FototecaTransform

The component uses X3ML resources for transforming XML data from the Fototeca collection.

In order to execute the software, please update the Spring beans file (found under src/main/resources/beans.xml) 
by setting up the corresponding details. More specifically the following can be configured: 

* location of the X3ML mappings files
* the generator policy file
* the UUID size
* the folder containing the original sources (XML data)
* the folder containing the transformed sources (RDF data)
