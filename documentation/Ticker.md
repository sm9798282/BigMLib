# Ticker V1

A class that provides increasing values upon each call of `tick()`.

## Copyright and Licensing notice

This file is supplied under a modified GNU LGPL V2.1 License.
Please see the provided LICENSE file for more information.

## Dependencies

- Java JVM
- `java.lang.*`

## FeatureList

- `new Ticker()`
- `new Ticker(int)`
- `| Ticker |   clone()`
- `_ boolean _  equals(Object)`
- `_ Class<?> _ getClass()`
- `_ int _      hashCode()`
- `_ void _     notify()`
- `_ void _     notifyAll()`
- `int          tick()`
- `int          tickN()`
- `Ticker       reset()`
- `Ticker       set()`
- `| String |   toString()`
- `_ void _     wait()`
- `_ void _     wait(long)`
- `_ void _     wait(long, int)`

(Methods marked with _ are inherited from `Object` and are both unchanged and untested;
Methods marked with | override their `Object` counterparts, and so are tested.)

## Internal methods

- `new Ticker(int, boolean)`

## Interfaces Implemented

- `Cloneable`
