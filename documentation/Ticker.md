# Ticker V1

A class that provides increasing values upon each call of `tick()`.

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Dependencies

- Java JVM
- `java.lang.*`

## Feature List

|Return type |Method Name                                 |
|------------|--------------------------------------------|
|`new Ticker`|`Ticker()`                                  |
|`new Ticker`|`Ticker(int)`                               |
|`Ticker`    |`clone()` (Overrides `Object.clone()`)      |
|`int`       |`tick()`                                    |
|`int`       |`tickN()`                                   |
|`Ticker`    |`reset()`                                   |
|`Ticker`    |`set()`                                     |
|`String`    |`toString()` (Overrides `Object.toString()`)|

### Object inherited methods

These methods are not overriden, and so are not tested.

|Return Type|Method Name      |
|-----------|-----------------|
|`boolean`  |`equals(Object)` |
|`Class<?>` |`getClass()`     |
|`void`     |`finalize()`     |
|`int`      |`hashCode()`     |
|`void`     |`notify()`       |
|`void`     |`notifyAll()`    |
|`void`     |`wait()`         |
|`void`     |`wait(long)`     |
|`void`     |`wait(long, int)`|

## Internal methods

- `new Ticker(int, boolean)`

## Interfaces Implemented

- `Cloneable`
