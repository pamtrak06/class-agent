class-agent
===========

### Objective ###
Java agent allow to instrument a Java class from a json configuration file.
Actually you give full class name (package + class Name) + set defaultConstructor to "true" and targeted classes will be modified by adding a default no-arg Constructor.

### Context ###
Useful by example with context where jackson is reading a json structure from third party Classes which haven't default no arg Constructor.
'''

    "org.codehaus.jackson.map.JsonMappingException: No suitable constructor found for type [simple type, class ...]: 
    can not instantiate from JSON object (need to add/enable type information?)"

### Configuration ###
By example, we want to add a default no arg constructor in class "test.mock.Center" which is accessible in a third party library (no jackson annotation possible).
Target class to be modified, must be present in configuration file : TransformDef.json, like this.
'''

    {
      "classDef" : [ {
        "classForName" : "test.mock.Center",
        "defaultConstructor" : true,
        "methodDefs" : [ ],
        "fieldDefs" : [ ]
      } ]
    }

Exported Jar file : class-agent.jar must be indicated in JVM argument like this :
-javaagent:class-agent.jar

So : before runtime, Center is intrumented with a default no arg constructor and then when jackson is reading the json file, there's no mapping error anymore.
 
