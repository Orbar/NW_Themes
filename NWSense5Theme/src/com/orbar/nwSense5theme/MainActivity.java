package com.orbar.nwSense5theme;

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

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";
	
	public static final String APP_DIRECTORY = "Notification Weather";
	public static final String THEMES_DIRECTORY = "Themes";
	
	String themeName;
	String primaryColor;
	String secondaryColor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Document theme = convertStringToXMLDocument(this, getXml("NW_Theme.xml"));
		
		themeName = theme.getElementsByTagName("ThemeName").item(0).getTextContent();
		primaryColor = theme.getElementsByTagName("PrimaryColor").item(0).getTextContent();
		secondaryColor = theme.getElementsByTagName("SecondaryColor").item(0).getTextContent();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void installTheme(View v) {
		
		/*
		 * These are the links for the Drawables. 
		 * You can change them to whatever you named your images
		 * the have to be valid resource names (no capital letters, not white spaces, etc...)
		 */
		Bitmap[] originalIcons = {
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_1),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_2),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_3),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_4),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_5),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_6),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_7),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_8),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_11),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_12),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_13),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_14),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_15),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_16),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_17),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_18),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_19),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_20),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_21),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_22),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_23),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_24),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_25),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_26),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_29),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_30),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_31),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_32),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_33),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_34),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_35),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_36),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_37),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_38),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_39),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_40),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_41),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_42),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_43),
				BitmapFactory.decodeResource( getResources(), R.drawable.weather_44),
				BitmapFactory.decodeResource( getResources(), R.drawable.preview),
				//BitmapFactory.decodeResource( getResources(), R.drawable.refresh),
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
				"preview",
				"refresh"
				
		};
		
		File sdCard = Environment.getExternalStorageDirectory();
		File extStorageDirectory = new File (sdCard.getAbsolutePath() + "/" + APP_DIRECTORY + "/" + THEMES_DIRECTORY + "/" + themeName);
		
		extStorageDirectory.mkdirs();
		
		FileOutputStream outStream;
		try {
		
			
			for (int i = 0; i < originalIcons.length; i++) {
				File file = new File(extStorageDirectory, fileNames[i] + ".png");
				
				outStream = new FileOutputStream(file);
			
			    originalIcons[i].compress(Bitmap.CompressFormat.PNG, 100, outStream);
			    
			    outStream.flush();
			    outStream.close();
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

	private String getXml(String path){

	    String xmlString = null;
	    AssetManager am = getAssets();
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
	
	private void copyAssets(String directory) {
	    AssetManager assetManager = getAssets();
	    String[] files = null;
	    try {
	        files = assetManager.list("");
	    } catch (IOException e) {
	        Log.e("tag", "Failed to get asset file list.", e);
	    }
	  
	    for (String filename : files) {
	    	Log.i(TAG, filename);
	    }
	    
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
	    }
	}
	
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}
}
