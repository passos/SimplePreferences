SimplePreferences
=================

Android library for helping simplify the preferences access. You can also implement your PreferenceStorage to save the preference into database or encrypt it.

Download
=========
Grab via Maven:
```xml
    <dependency>
        <groupId>com.ioenv</groupId>
        <artifactId>preferences-library</artifactId>
        <version>1.0</version>
    </dependency>
```
or Gradle:
```groovy
    compile 'com.ioenv:preferences-library:1.0'
```

Uasge
=====

initialization
--------------
Initialize with default settings
```java
Preferences.init(getContext());
```
  
Initialize with an encoded preferences storage  
```java
Preferences.init(new EncodedPreferenceStorage(getContext()));
```

get/set simple preference
----------------------
get/set by `Preferences.set(KEY, VALUE)` and `Preferences.get(KEY, DEFAULT_VALUE)`
```java
Preferences.set("str_config", "String");
Preferences.set("int_config", 1234);
Preferences.set("long_config", 1234567890L);
Preferences.set("bool_config", true);
Preferences.set("float_config", 123.45);
```
    
```java
Preferences.get("str_config"， "Default");
Preferences.get("int_config"， 0);
Preferences.get("long_config"， 0L);
Preferences.get("float_config"， 0f);
Preferences.get("bool_config"， false);
```

get/set object preference
-------------------------
```java
class DataObject {
    long timestamp;
    String message;
    public DataObject(long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}

Preferences.set("obj_config", new DataObject(1, "Hi"));
DataObject data = Preferences.get("obj_config"， DataObject.class, null);
```

get/set list preference
-----------------------
string list
```java
Preferences.setStrings("str_list", new ArrayList<String>() {{
    add("String 1");
    add("String 2");
    add("String 3");
}};

List<String> result = Preferences.getStrings("str_list");
```

object list
```java
List<DataObject> objList = new ArrayList<DataObject>() {
objList.add(new DataObject(1, "A"));
objList.add(new DataObject(2, "B"));
objList.add(new DataObject(3, "C"));
Preferences.set("obj_list", Preferences.toJson(objList));

List<DataObject> anotherList = new ArrayList<DataObject>() {
    {
        add(new DataObject(1, "A"));
        add(new DataObject(2, "B"));
        add(new DataObject(3, "C"));
    }
};
Preferences.set("another_list", Preferences.toJson(new ArrayList<DataObject>(anotherList)));

List<DataObject> result = Preferences.getList("obj_list", new TypeToken<ArrayList<DataObject>>() {}.getType());
```
  
FAQ
===
For any questions, please submit to [issues][2].

Developed By
============

* Jinyu Liu (Simon) - <simon.jinyu.liu@gmail.com>

License
========

    Copyright 2015 Jinyu Liu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://search.maven.org/remote_content?g=com.ioenv&a=preferences-library&v=LATEST
 [2]: https://github.com/passos/SimplePreferences/issues
 [3]: http://passos.github.com/simple-preferences/
