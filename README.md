# picsback

Why write this program?
Over the years I have collected many pictures and backed them up in several places and locations.
This program is intended to collect images from multiple locations and organize them into a single location, and files renamed to the date/time they were taken.  This will naturally sort the images into seqence of events over timme.


# TODO

Basic Features

* detect pictures as exact match
* extract picture capture date/time
* extract picture geo data
* read pictures from multiple file locations, not just one directory tree
* file renaming
* moving files to new location (single directory, or sorted directory by image year/month/day?)

Advanced Features

* Detect images that may have been rotated, but are the same...compare rotated image size with other images
* Detect images that may have been resized, but are the same...make the two images the same size, take small pieces of the image and compare the 'average color' to within a percent of difference (http://stackoverflow.com/questions/17282272/comparing-images-to-find-duplicates).

Testing

* Create test images of different formats with Java
