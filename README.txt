1. Create parent module
mvn archetype:generate -DgroupId=com.dell.apm.testproject -DartifactId=APMTestProject -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

2. Create child APMTestWebAPP module
mvn archetype:generate -DgroupId=com.dell.apm.testwebapp -DartifactId=APMTestWebAPP -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.dell.apm.testserviceapp -DartifactId=APMTestServiceAPP -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

3. Create an Eclipse project with Maven:
mvn eclipse:eclipse
Create an Intellij project with Maven:
mvn idea:idea

4. Build an war file:
mvn package

5. Generate client java code for webservice:
mvn jaxws:wsimport
