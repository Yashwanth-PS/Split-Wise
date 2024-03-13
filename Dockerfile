# Environment Set Up - Base Image with Java Runtime
# Create a New Ubuntu Machine that has Open JDK 17
FROM openjdk:17

# Maven -> SplitWise -> LifeCycle -> Package -> JAR File will be created
# Copy the Jar File of Our Application in the Local
# To the Home Folder of the Machine (Container) in Server as app.jar
COPY target/SplitWise-0.0.1-SNAPSHOT.jar app.jar

# Command to run the Java Application --> java -jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]