# Most-Common-3-Page-Sequence-App

- Overview -
This project creates an Android app that downloads a text file from a server, parses the contents, and displays the results.

- Download -
The file is retrieved from the following URL:

http://dev.inspiringapps.com/Files/IAChallenge/30E02AAA-B947-4D4B-8FB6-9C57C43872A9/Apache.log

- Parse -
The file is a standard Apache web server access log. The problem is to find out the most common three-page sequences in the file. A three-page sequence is three consecutive requests from the same user. In case it helps simplify your processing, you can assume the following about the contents:

* Log entries are in ascending chronological order
* An IP address is unique per user

- Display -
The results are displayed in descending order by sequence frequency, so the most common sequence appears first and the least common appears last. Each sequence is displayed by the pages that make up the sequence and the number of times that the sequence appears. The results are then displayed in a RecyclerView. 


- Example -
Based on the following sample data, sequence “Page 1, Page 2, Page 3” is the most common. It appears three times in the results:

User A: Page 1

User B: Page 1

User B: Page 2

User B: Page 3

User B: Page 2

User A: Page 2

User A: Page 3

User A: Page 4

User A: Page 1

User A: Page 2

User A: Page 3

## Screenshot/Walkthrough

Here's a screenshot of the application:

<img src='https://i.imgur.com/Qz5eDl0.jpg' title='User Story 1' width='' alt='User Story 1' />

## Open source libraries 

- [Retrofit](http://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java applications
- [RxAndroid](https://github.com/ReactiveX/RxJava) - RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- [Dagger 2](https://github.com/google/dagger) - A fast dependency injector for Android and Java. 

## Copywrite / License
    
    Copyright 2018 Chris Jones at Chris J Mobile Apps

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
