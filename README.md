# Project 1 - *NYTimesSearch*

**NYTimesSearch** displays the news articles from NewYork Times. The app utilizes the NewYork Times API to display news articles,images.

Submitted by: **Rakesh Nair Paruthikkat**


Time spent: **25** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can enter a search query that will display a grid of news articles using the thumbnail and
      headline from the New York Times Search API.
* [x] User can click on "filter" icon which allows selection of advanced search options to filter articles.
* [x] User can configure advanced search filters applied to the articles search.
* [x] Subsequent searches will have any filters applied to the search results.
* [x] User can tap on any article in results to view the contents in an embedded browser
* [x] User can scroll down "infinitely" to continue loading more news articles.

The following **optional** features are implemented:

* [x] Robust error handling, check if internet is available, handle error cases, network failures.
* [x] Use the ActionBar SearchView or custom layout as the query box instead of an EditText.
* [x] Replace Filter Settings Activity with a lightweight modal overlay.
* [x] Use the RecyclerView with the StaggeredGridLayoutManager to display improve the grid of image results.
* [x] Use the RecyclerView with the StaggeredGridLayoutManager to display improve the grid of image results.
* [x] Leverage the popular GSON library to streamline the parsing of JSON data.
* [x] Consume the New York Times API using the popular Retrofit networking library instead of Android Async HTTP.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='<http://i.imgur.com/TjMZWEq.gif>' title='Video Walkthrough' width='' alt='Video Walkthrough' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Open-source libraries used

- [Retrofit](https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Stetho](https://github.com/facebook/stetho) - Stetho is a debug bridge for Android applications,enabling the powerful Chrome Developer Tools and much more.
- [picasso transformations](https://github.com/wasabeef/picasso-transformations) - An Android transformation library providing a variety of image transformations for Picasso
- [Google gson](https://github.com/google/gson) - A Java serialization/deserialization library that can convert Java Objects into JSON and back.


## License

    Copyright [2017] [Rakesh Nair Paruthikkat]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.