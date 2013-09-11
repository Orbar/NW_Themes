NW_Themes
=========

Sample Themes for Notification Weather Pro


Instructions
-------------

A theme is defined using 3 group of files:

* NW_Theme.xml - main theme definition file
* Weather_???.png - weather images
* themeInstaller.java - java class that installs the theme. You will most likely not have to edit this file


Theme file
---------------


NW_theme.xml

Placed into the assets directory

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Theme>
	<ThemeName>Sense 5</ThemeName>
	<Package>com.orbar.nwSense5theme</Package>
	<ThemeVersion>2</ThemeVersion>
	<AllowIconColorFilter>True</AllowIconColorFilter>
	<RefreshIcon>True</RefreshIcon>
	<AllowRefreshIconColorFilter>True</AllowRefreshIconColorFilter>
	<PrimaryColor>FFFFFF</PrimaryColor>
	<SecondaryColor>BEBEBE</SecondaryColor>
</Theme>
```

* ThemeName (required) - The name of the theme. appears in the theme selector fragment in the top left of the theme card
* Package (optional) - The package name used for the play store listing. Allows the user to long press on the theme to open in the playstore and rate the theme
* ThemeVersion (required) - The version number of the theme. Appears in the top right of the theme card. Serves no functional purpose
* AllowIconColorFilter (optional) - False if omitted. Determins if to allow to pass icon through a color filter. useful for black and white icons, such as the default theme
* RefreshIcon (optional) - False if omitted. False means that theme will use the default refresh icon. True means it will use a supplied refresh image
* AllowRefreshIconColorFilter (optional) - True if omitted. Determins if to allow to pass refresh icon through a color filter. useful for black and white icons, such as the default theme
* PrimaryColor and SecondayColor (required) - Used to apply color filters to the texts and icons. See below for a more detailed explanation.

Image Files
-----------

You should put all of your icons in the drawable-xhdpi directory. The code will automatically resize the icons for the device running it. You may put the code in the other drawable folders if you prefer, but you would have to adjust the icons sizes accordingly.

The theme requires that you supply all 41 weather icons. however, if some of your icons are the same (such as ‘partly cloudy’ and ‘mostly cloudy’) you can supply 1 icon and refer to it twice in the originalIcons bitmap array inside of the themeInstaller class.
This has the benefit of reducing the size of the download. (See NWNowTheme to see how this is done)

If you would like to supply a preview image (and you should) and/or a refresh icon, place them into the drawable-xhdpi directory. The code will detect if they are present and copy them if needed.

If you do not supply a preview image, a ‘Preview Unavailable' will be displayed instead).



Icon Sizes
-------------

<table>
  <tr>
    <th>Name</th><th>Size</th><th>padding</th>
  </tr>
  <tr>
    <td>Preview.png</td><td>720x310 px</td><td>0px</td>
  </tr>
  <tr>
    <td>Refresh.png</td><td>48x48 px</td><td>2-3 px</td>
  </tr>
  <tr>
    <td>Weather Icons</td><td>190x152 px</td><td>4-5 px</td>
  </tr>
</table>


Color filter
----------

The color filter works by applying a filter on top of the original text or icon. For example if the original icon is white and the filter is red, then the icon will appear red. However, if your original icon is yellow, and the filter is blue, the icon will appear green and not blue. For this reason, if your icons are not monochromatic you should set AllowIconColorFilter to false.  

 
Play Store Listing
----------------------

The Notification Weather's theme chooser button will search the Play Store for apps containing the term 'NWPT' for Notification Weather Pro theme.
It is recommended to name your installer app something like 'NWPT {insert name}. eg. NWPT Sense 5, NWPT Now.


Don't forget to make sure that the Package varialble in NW_Theme.xml matches the package in AndroidManifest. This will ensure that the user will be able to long press on the theme to open it in the play store and leave a comment/rating.


Developement Environment
------------------------

This section is just to get you running in case you don't know how to setup a dev environment.

Download and install java JRE 1.6 (1.7 will probably work as well) http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html

Download and install Android ADT Bundle (includes Eclipse, Android SDK, platform-tools (for 4.3), and ADT plugin) http://developer.android.com/sdk/index.html

Use Android SDK Manager to install APIs 16, 17, 18 for android 4.1, 4.2, and 4.3 http://developer.android.com/tools/help/sdk-manager.html

Use Git/Egit to checkout 1 of the 2 themes in this repository (or just download in zip format) and start having fun.


