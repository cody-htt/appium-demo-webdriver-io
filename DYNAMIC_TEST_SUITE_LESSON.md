# WHY
* Device List is not always static
* More control on the way we provide the test parameters bas on platform
* Flexibility when we add new tests

## HOW
* Get all test packages info
* Get the current platform 
* Build the suite dynamically 

## Build up Jarfile
* Build a jar file with shade plugin
* Handle reading file content from resources folder
* Provide test parameters and run the jar file

## Setup Upstream/down stream jobs
* Regression for all platforms
* Divide tests into device list to save testing time

## More
_Base on what we had until now, we can see we can even control our
 test base on more info provide from the test like filter a test group to run_

## References
- https://testng.org/doc/documentation-main.html#running-testng-programmatically

