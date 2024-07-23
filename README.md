Project demonstrates incorrect determination of expiration date in some cases.

Build: `mvn clean compile dependency:copy-dependencies`

Run: `java -cp target/classes;target/dependency/bcpg-debug-jdk18on-1.78.1.jar;target/dependency/bcprov-jdk18on-1.78.1.jar;target/dependency/bcutil-jdk18on-1.78.1.jar com.example.pgp.test.App`

Output is a number of exiration period for exported [from here](https://keyserver.ubuntu.com/pks/lookup?search=60ec1b93cefd283d0ac228470ca7938287683fc0&fingerprint=on&op=index
) pgp key (ASC file located [here](https://github.com/bvfalcon/openpgp-expire-test/blob/master/src/main/resources/test.asc)). Correct value is 0, but determined incorrect value is 1141783116. 
