# Cucumber-Java ShopNonstop

The goal of this workshop is to implement Shop Nonstop web shop
where delicious chocolates are sold. The web shop does not have a user
interface, so we'll only work directly on the backend using REST services. The
project is set up with Maven 3 and Java 8, you'll need to install these tools
before you can get started. An IDE is nice to have too, IntelliJ or Eclipse are
good choices. 

We'll drive the development with Cucumber scenarios. For now there is a single
feature file with one scenario. The scenario has three steps, two of them
pending. See if you can make them all pass!

Use a test driven approach: write a scenario and implement the step definitions
before writing the domain code that makes the scenarios pass. Remember that
Cucumber has two "stages" it can complain about: you have to have the step
definitions and then make the test pass. The output from cucumber will help you
generate the stub code for the step definitions if your IDE doesn't do it for
you. 

The cucumber setup is forked from https://github.com/cucumber/cucumber-java-skeleton.git

## Get the code

If you have or create a github account you can clone the workshop using git
with:

    git clone https://github.com/cucumber/cucumber-java-shop-nonstop.git
    cd cucumber-java-shop-nonstop

Or use Subversion if that's your preferred version control setup:

    svn checkout https://github.com/cucumber/cucumber-java-shop-nonstop/trunk cucumber-java-shop-nonstop
    cd cucumber-java-shop-nonstop

Or simply [download a
zip](https://github.com/cucumber/cucumber-java-shop-nonstop/archive/master.zip)
file if you don't have or don't want to get a github account. 


## Build the project

Open a command window, go to the main project directory (where the pom.xml is) and run:

    mvn clean install

This compiles the code, runs Cucumber features using Cucumber's JUnit runner and
builds the executable jar file. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

## The project setup

The web shop itself is hosted on a little web server and exposes itself to the
world with REST services. This project uses [Spring Boot]
(http://projects.spring.io/spring-boot/) to set up the web server, so the main
class is annotated with `@SpringBootApplication` and basically uses Spring Boot
to setup a server. 

The appliation can be started from your IDE by running `Main.java` (in
src/main/java/com/shop/nonstop/) or by running the jar file you get in the
target directory after you've run `maven clean install`. Run the jar file with 

    java -jar cucumber-java-shop-nonstop-0.0.1.jar  
 
This gives you a web server running on localhost which listens to port 8080. You can check that
it's running by opening a browser tab at

    http://localhost:8080/hello

You should get "Hello!" as a reply from the rest service.


#Cucumber details

## Cucumber test setup

The feature files, where all scenarios are collected, can be found in
src/test/resources and follows the package convention of the domain code
(src/main/java). 

The step definitions (glue code) can be found in src/test/java, also following
the package convention of the domain code. The main test runner class,
RunCukesTest.java, has annotations both for Cucumber
and Spring Boot, so that maven knows how to run the tests and will run the tests
with the web server (use maven test or maven clean install). 


## Overriding options

The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc.
When you use the JUnit runner, these options are generated from the `@CucumberOptions` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the
`cucumber.options` system property. The general form is:

Using Maven:

    mvn -Dcucumber.options="..." test

Let's look at some things you can do with `cucumber.options`. Try this:

    -Dcucumber.options="--help"

That should list all the available options.

*IMPORTANT*

When you override options with `-Dcucumber.options`, you will completely override whatever options are hard-coded in
your `@CucumberOptions` or in the script calling `cucumber.api.cli.Main`. There is one exception to this rule, and that
is the `--plugin` option. This will not _override_, but _add_ a plugin. The reason for this is to make it easier
for 3rd party tools (such as [Cucumber Pro](https://cucumber.pro/)) to automatically configure additional plugins by appending arguments to a `cucumber.properties`
file.

### Run a subset of Features or Scenarios

Specify a particular scenario by *line* (and use the pretty plugin, which prints the scenario back)

    -Dcucumber.options="classpath:skeleton/belly.feature:4 --plugin pretty"

This works because Maven puts `./src/test/resources` on your `classpath`.
You can also specify files to run by filesystem path:

    -Dcucumber.options="src/test/resources/skeleton/belly.feature:4 --plugin pretty"

You can also specify what to run by *tag*:

    -Dcucumber.options="--tags @bar --plugin pretty"

### Running only the scenarios that failed in the previous run

    -Dcucumber.options="@target/rerun.txt"

This works as long as you have the `rerun` formatter enabled.

### Specify a different formatter:

For example a JUnit formatter:

    -Dcucumber.options="--plugin junit:target/cucumber-junit-report.xml"
