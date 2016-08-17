# Android home assignment

Use the supplied Android project (gradle) template to implement the following requirements: 

**Requirements**

* The app should fetch remote data from three endpoints (data is in JSON format).
Each endpoint returns a different format of JSON data, that contains an image url, title and a subtitle/text.
Each endpoint can return **more than one result**.
* The predefined URLs for the different endpoints are to be found under ```Consts``` class.
* **Data Mapping:**
    * Data source A : 
        ```
        {
          "stories": [
            {
              "title": "Interesting News!",
              "subtitle": "You're not gonna believe this...",
              "imageUrl": "https://pbs.twimg.com/profile_images/658218628127588352/v0ZLUBrt.jpg"
            }
            ...
            ]
        }
        ```
    * Data source B : 
        * The relevant fields here are "header", "description" and "picture".
        
        ```
         {
           "metadata": {
             "this": "isnotimportant",
             "innerdata": [
               {
                 "aticleId": 1,
                 "articlewrapper": {
                   "header": "WOW",
                   "description": "SHIGAON"
                 },
                 "picture": "https://pbs.twimg.com/profile_images/2084187780/avib_400x400.jpg"
               }
               ...
               ]
           }
         }            
        ```
    
    * Data source C :
         * subtitle here is a concat of subLine1 + subLine2.
     
        ```
        [
          {
            "topLine": "Latest article",
            "subLine1": "And here is the ",
            "subline2": "subtitle!",
            "image": "http://c-sf.smule.com/s25/arr/1d/4c/f0cf4342-e875-4601-b47e-c74a8d021d8d.jpg"
          }
          ...
        ]
        ```
        
* The data should be parsed, aggregated and presented in a predefined ```RecyclerView``` in ```MainActivity```
* The layout for each item in the ```RecyclerView``` is already written for you at res/layout/card_item.xml
* ```card_item.xml``` contains ```ImageView``` and two ```TextView``` one for the title and the other for subtitle/text, which you will populate from the data you fetched and parsed from the different servers.
* Data should be presented in the ```RecyclerView``` **only** when **all** of the data is fetched from the different endpoints.
  (You can show a cool progress bar meanwhile)
  
* Make sure your code is easily extensible for any future data endpoints, easy to understand for future developers, and beautiful!
* Feel free to make the UI as delightful as you like (animations and so on).

**Important**

   * You are encouraged to use any 3rd party library that you like working with, i.e. [Volley](https://developer.android.com/training/volley/index.html).

**Bonus**

  * Add a caching layer for each data source. 
    * Cache stale times:
        Source A - 5 min
        Source B - 30 min
        Source C - 60 min
