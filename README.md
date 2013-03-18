#nominatimJavaSupport

##About

nominatimJavaSupport bring you support for parsing or querying Nominatim API.
Nominatim is a part of OpenStreetMap project that help you to look for cities and points of interest.
This librairy is designed to work on all platform including Android, AppEngine, etc...

##What does this library do for you ?

+ Querying : You do not have to manage complicated URL, this librairy provide an easy-to-use URL builder object.
+ Parsing : You do not want to manage complicated JSON parsing, this librairy support parsing into objects.

##What does not this library ?

In order to handle multiplatform, no HTTP handling has been integrated to nominatimJavaSupport.
You must use another librairy to do the job :
+ Apache HttpComponents : Android, etc...
+ URLFetcher : Google AppEngine

##Usages

###Search

Example 1 :  http://nominatim.openstreetmap.org/search?q=135+pilkington+avenue,+birmingham&format=xml&polygon=1&addressdetails=1
```java
String url = (new NominatimQueryBuilder(Format.XML)).query("135 pilkington avenur, birmingham")
                  .polygon(true)
                  .addressdetails(true)
                  .build();
```

###Reverse

Reverse geocoding is not supported by this version. May be added later.

##License

This library is covered by Apache v2.
