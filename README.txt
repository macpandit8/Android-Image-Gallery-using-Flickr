----------------------------------------------------------------------------------------
--------------------------Android Image Gallery using Flickr----------------------------
----------------------------------------------------------------------------------------
-------------------------------Developed & Designed By----------------------------------
-----------------------------------Mayank Pandit----------------------------------------
----------------------------------------------------------------------------------------

---Description of Problem and Solution---

PROBLEM - Utilizing the Android framework, design a mobile app that will show a series of images as a gallery,
	  fetched from Flickr API

Designing Process - I have thought of this problem as a individual image view on individual grids on GridView
		    in Android layout.Each grid shows image fetched from flickr API and populate gallery.
		 >> For showing Title, Size and Dimensions, I have used dialogue box which pops up on the screen
		    when any photo is clicked. It shows title, size, height and width of the image

SOLUTION - I have used AsyncTask which basically is used to run long running task seperately from the main UI thread.
	>> The reason of using AsyncTask over Service is, AsyncTask can update UI after completing task
	>> MainActivity.class works as an entry point for the application
	>> Upon starting of an application, ImageSearchTask executes the provided Flickr link from which JSON data
	   are fetched.
	>> To minimize the procession decoding JSON data, I have used Gson object which seperates elements from 
	   the JSON data required to construct the URL of the image
	>> This image is fetched and then populates the GridView list one by one to make Image Gallery
	>> Parallelly image URL is converted into the bitmap by another AsyncTask which ultimately is used to find
	   out the size and dimensions of the image
	>> When any image is clicked, dialogue box pops up which shows Title, size of image (in bytes), height &
	   width (in pixels) of the image

Trade Off - If given more time, I could be able to add functionalities for searching images by keyword provided in the EditText
	    and Search icon on the main UI


----Tools and Languages used----
>> Flickr API
>> Gson library
>> Android Studio 3.1.3
>> Android Emulator
>> Languages - Java, XML


Thank You