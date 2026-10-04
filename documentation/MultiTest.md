# MultiTest

By implementing this interface, a class or interface will be compatible with JUnit powered testing.

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Dependencies

- Java JVM
- `java.lang.*`
- `java.util.*`
- `lib/hamcrest-core-1.3.jar`
- `lib/junit-4.13.2.jar`

## Methods to implement

- `void setupTestData()`
    Calling this method will instantiate any testing data that is shared between different tests of different methods.
- `@Test`
  `void allTests()`
    Calling this method should result in each of the differing tests being called in sequence. If this method fails to pass all the tests, either the source code or the tests require debugging.
