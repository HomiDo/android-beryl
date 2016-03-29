# Introduction #

Registry of interfaces that can be queried by other objects. The primary goal is to provide a way to decouple Fragments while allowing them to communicate with each other through these exposed interfaces. This class is not specific to Activities and Fragments but can be used in any situation where multiple components need to communicate.

## Pattern Setup ##

  1. Create a class that implements the IContractMediator interface. This class will hold the ContractRegistry object.
  1. Determine and create the contracts (interface) that the classes would want exposed. These interfaces must extend RegisterableContract.
  1. For each component, create non-static inner classes for the each interface they will expose. This is preferred over having the component itself implement the interface but that is supported as well.
  1. When wiring up the objects together get the handle of the ContractsRegistry from the class that implements IContractMediator. Add the object to the registry via the .add(Object) method.
  1. Likewise when removing the object call .remove(object) against the registry otherwise a GC memory-leak will occur.

# JavaDocs #

[JavaDoc for ContractRegistry](http://docs.android-beryl.googlecode.com/hg/org/beryl/app/ContractRegistry.html)