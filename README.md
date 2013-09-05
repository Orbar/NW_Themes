NW_Themes
=========

Sample Themes for Notification Weather Pro


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
	<PrimaryColor>FFFFFF</PrimaryColor>
	<SecondaryColor>BEBEBE</SecondaryColor>
</Theme>
```

* ThemeName (required) - The name of the theme. appears in the theme selector fragment in the top left of the theme card
* Package (optional) - The package name used for the play store listing. Allows the user to long press on the theme to open in the playstore and rate the theme
* ThemeVersion (required) - The version number of the theme. Appears in the top right of the theme card. Serves no functional purpose
* AllowIconColorFilter (optional) - False if omitted. Determins if to allow to pass icon through a color filter. useful for black and white icons, such as the default theme
* RefreshIcon (optional) - False if omitted. False means that theme will use the default refresh icon. True means it will use a supplied refresh image
* PrimaryColor and SecondayColor (required) - Used to apply color filters to the texts and icons. See below for a more detailed explanation.

Image Files
-----------

You should put all of your icons in the drawable-xhdpi directory. The code will automatically resize the icons for the device running it. You may put the code in the other drawable folder if you prefer.

The theme requires that you supply all 40 weather icons. however, if some of your icons are the same (such as ‘partly cloudy’ and ‘mostly cloudy’) you can supply 1 icon and refer to it twice in the originalIcons bitmap array inside of the themeInstaller class.
This has the benefit of reducing the size of the download.

If you would like to supply a preview image (and you should) and or a refresh icon, place them into the drawable-xhdpi directory. The code will detect if they are present and copy them if needed.

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
