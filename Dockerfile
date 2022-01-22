FROM amazoncorretto:11-alpine-jdk as build

RUN apk add --update ca-certificates && rm -rf /var/cache/apk/* && \
    find /usr/share/ca-certificates/mozilla/ -name "*.crt" -exec keytool -import -trustcacerts \
    -keystore /usr/lib/jvm/java-11-amazon-corretto/lib/security/cacerts -storepass changeit -noprompt \
    -file {} -alias {} \; && \
    keytool -list -keystore /usr/lib/jvm/java-11-amazon-corretto/lib/security/cacerts --storepass changeit

ENV MAVEN_VERSION 3.8.4
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH
RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    mv apache-maven-$MAVEN_VERSION /usr/lib/mvn

WORKDIR /workspace/app

COPY pom.xml .
COPY src src
RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM amazoncorretto:11-alpine-jdk
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.sclw.SclwCreateApplication"]
EXPOSE 8000