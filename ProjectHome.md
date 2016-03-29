## What is Beryl ##
Beryl library is a set of APIs to simplify Android development. Much of the code comes from reusable components from published apps.

## Features ##

  * Fragment/loose component communication pattern. ContractRegistry
  * Asynchronous intents library
  * Unified File I/O handling
  * Safe bitmap loading.
  * Extended Logging APIs
  * Common Intents library (We need more here!)
  * Helpers for Android backwards compatibility.
  * Extended Location APIs with Controllers
  * Some custom widgets
  * [Well Documented with Sample Code](http://docs.android-beryl.googlecode.com/hg/index.html)
  * [Backed by unit tests](http://code.google.com/p/android-beryl/source/browse?repo=test).

_To get a feel of what's available visit the [API Documentation](http://docs.android-beryl.googlecode.com/hg/index.html) and [Wiki](http://code.google.com/p/android-beryl/w/list) to see what is available._

## Building ##
[Building Beryl](BuildingBeryl.md) is pretty easy.

## Organization ##
Beryl is now categorized into core, extension, and experimental jar files. Since the library might be significantly large it is encouraged that you use ProGuard in order to minimize code size. A standard ProGuard file is shipped with beryl. It also includes the definitions for the v4 compatibility library for Android.

Beryl is compiled against the latest version of Android and is intended to work for Android 2.1 and later. Many parts of the API work fine and have backwards compatibility components down to Android 1.5.

## Development Status ##
_Beryl is still a young project. It undergoes many changes for each version. Classes may be removed or moved each release. If there are any bugs please report them or submit a fix._