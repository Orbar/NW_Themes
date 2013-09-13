package com.orbar.nwsense5theme;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class ThemeInstaller {

public static final String TAG = "ThemeInstaller";
	
	public static final String APP_DIRECTORY = "Notification Weather";
	public static final String THEMES_DIRECTORY = "Themes";
	public static final String THEME_FILE = "NW_Theme.xml";
	public static final String NOMEDIA = ".nomedia";
	
	private static String themeName;
	private static String primaryColor;
	private static String secondaryColor;
	
	private static Context mContext;
	
	public static void installTheme(Context context, int previewResourceId, int refreshResourceId, int backgroundResourceId, int backgroundExtendedResourceId) {
		
		mContext = context;
		
		Document theme = convertStringToXMLDocument(mContext, getXml(THEME_FILE));
		
		themeName = theme.getElementsByTagName("ThemeName").item(0).getTextContent();
		primaryColor = theme.getElementsByTagName("PrimaryColor").item(0).getTextContent();
		secondaryColor = theme.getElementsByTagName("SecondaryColor").item(0).getTextContent();
		
		/*
		 * These are the links for the Drawables. 
		 * You can change them to whatever you named your images
		 * the have to be valid resource names (no capital letters, not white spaces, etc...)
		 */
		Bitmap[] originalIcons = {
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_sunny),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_sunny),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_sunny),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_intermittent_clouds),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_haze),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_cloudy),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_dreary),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_foggy),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_showers),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_showers),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_sunny_with_showers),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_thunderstorms),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_thunder),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_sunny_with_thunder),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_rain),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_flurries),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_flurries),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_sunny_with_flurries),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_snow),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_snow),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_ice),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_sleet),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_freezing_rain),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_rain_and_snow_mix),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_hot),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_cold),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_windy),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_clear),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_clear_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_cloudy_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_intermittent_clouds_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_haze_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudey_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_cloudy_with_showers_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_showers_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_cloudy_with_thunder_showers_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_mostly_cloudy_with_thunder_showers_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_cloudy_with_flurries_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_partly_cloudy_with_snow_night),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_tornado),
				BitmapFactory.decodeResource( mContext.getResources(), R.drawable.weather_na),
				BitmapFactory.decodeResource( mContext.getResources(), previewResourceId),
				BitmapFactory.decodeResource( mContext.getResources(), refreshResourceId),
				BitmapFactory.decodeResource( mContext.getResources(), backgroundResourceId),
				BitmapFactory.decodeResource( mContext.getResources(), backgroundExtendedResourceId),
			};
		
		
		
		/*
		 * These are the actual file names that Notification Weather Pro will use to 
		 * access the files.
		 * 
		 * ******************   DO NOT CHANGE THESE   *********************
		 */
		String [] fileNames = {
				"weather_sunny",
				"weather_mostly_sunny",
				"weather_partly_sunny",
				"weather_intermittent_clouds",
				"weather_haze",
				"weather_mostly_cloudy",
				"weather_cloudy",
				"weather_dreary",
				"weather_foggy",
				"weather_showers",
				"weather_mostly_cloudy_with_showers",
				"weather_partly_sunny_with_showers",
				"weather_thunderstorms",
				"weather_mostly_cloudy_with_thunder",
				"weather_partly_sunny_with_thunder",
				"weather_rain",
				"weather_flurries",
				"weather_mostly_cloudy_with_flurries",
				"weather_partly_sunny_with_flurries",
				"weather_snow",
				"weather_mostly_cloudy_with_snow",
				"weather_ice",
				"weather_sleet",
				"weather_freezing_rain",
				"weather_rain_and_snow_mix",
				"weather_hot",
				"weather_cold",
				"weather_windy",
				"weather_clear",
				"weather_mostly_clear_night",
				"weather_partly_cloudy_night",
				"weather_intermittent_clouds_night",
				"weather_haze_night",
				"weather_mostly_cloudey_night",
				"weather_partly_cloudy_with_showers_night",
				"weather_mostly_cloudy_with_showers_night",
				"weather_partly_cloudy_with_thunder_showers_night",
				"weather_mostly_cloudy_with_thunder_showers_night",
				"weather_partly_cloudy_with_flurries_night",
				"weather_partly_cloudy_with_snow_night",
				"weather_tornado",
				"weather_na",
				"preview",
				"refresh",
				"background",
				"background_extended"
				
				
		};
		
		File sdCard = Environment.getExternalStorageDirectory();
		File extStorageDirectory = new File (sdCard.getAbsolutePath() + "/" + APP_DIRECTORY + "/" + THEMES_DIRECTORY + "/" + themeName);
		
		extStorageDirectory.mkdirs();
		
		FileOutputStream outStream;
		try {
		
			
			for (int i = 0; i < originalIcons.length; i++) {
				if (originalIcons[i] != null){
				
					File file = new File(extStorageDirectory, fileNames[i] + ".png");
					
					outStream = new FileOutputStream(file);
				
				    originalIcons[i].compress(Bitmap.CompressFormat.PNG, 100, outStream);
				    
				    outStream.flush();
				    outStream.close();
				} else {
					Log.i("null check", "icon " + i + " was null");
				}
			}
			
			//write the xml file as well	
			copyAssets(sdCard.getAbsolutePath() + "/" + APP_DIRECTORY + "/" + THEMES_DIRECTORY + "/" + themeName);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String getXml(String path){

	    String xmlString = null;
	    AssetManager am = mContext.getAssets();
	    try {
	        InputStream is = am.open(path);
	        int length = is.available();
	        byte[] data = new byte[length];
	        is.read(data);
	        xmlString = new String(data);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }

	    return xmlString;
	}

	public static Document convertStringToXMLDocument(Context context, String src) {
		Document dest = null;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;

		try {
			parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dest;
	}
	
	private static void copyAssets(String directory) {
	    AssetManager assetManager = mContext.getAssets();
	    String[] files = {THEME_FILE};
	    
	    for(String filename : files) {
	        InputStream in = null;
	        OutputStream out = null;
	        try {
	          in = assetManager.open(filename);
	          File outFile = new File(directory, filename);
	          out = new FileOutputStream(outFile);
	          copyFile(in, out);
	          in.close();
	          in = null;
	          out.flush();
	          out.close();
	          out = null;
	        } catch(Exception e) {
	            Log.e(TAG, "Failed to copy asset file: " + filename, e);
	        }   
	        
	        // Make .nomedia file
	        Log.i(TAG, directory+ "/" + NOMEDIA);
	        File noMedia = new File(directory + File.separator + NOMEDIA);
	        
	        try {
				noMedia.createNewFile();
			} catch (IOException e) {
				Log.e(TAG, "Failed to create no media file", e);
			}
	    }
	}
	
	private static void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];;
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}
}
