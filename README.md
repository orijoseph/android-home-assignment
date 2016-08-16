# Android home assignment

Use the supplied Android project (gradle) template to implement the following requirements: 

**Requirements**

* The app should fetch remote data from three servers (data is in JSON format).
Each server returns a different format of JSON data, that contains an image url, title and a subtitle/text.
Each server can return more than one result.
* The predefined URLs for the different servers are to be found under ```Consts``` class.
* The data should be parsed, aggregated and presented in a predefined ```RecyclerView``` in ```MainActivity```
* The layout for each item in the RecyclerView is already written for you at res/layout/card_item.xml
* ```card_item.xml``` contains ```ImageView``` and two ```TextView``` one for the title and the other for subtitle/text, which you will populate from the data you fetched and parsed from each server.
* Data should be presented in the ```RecyclerView``` **only** when **all** of the data is fetched from the different servers.
  (You can show a cool progress bar meanwhile)

**Important**
* You are encouraged to use any 3rd party library that you like working with, i.e. [Volley](https://developer.android.com/training/volley/index.html).

**Bonus**
* We love animations! seriously! show us what you can do with items in the ```RecyclerView```, or the transition between loading View and contentView.
