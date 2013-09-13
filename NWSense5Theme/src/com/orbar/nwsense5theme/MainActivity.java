package com.orbar.nwsense5theme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";

    protected ProgressDialog progressDialog; 
    
    boolean hasPreview = false;
	
    int previewResourceId = 0;
    int refreshResourceId = 0;
    int backgroundResourceId = 0;
    int backgroundExtendedResourceId = 0;
	
    final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_installer);
		
		
		/*
		 *  Check if preview drawable is supplied.
		 *  If yes, get it's resource ID
		 */
		
		try {
	        previewResourceId = getResources().getIdentifier("preview", "drawable", getPackageName());
	        if (previewResourceId != 0) setHasPreview(true);
	    }

	    catch(Exception e) {
	    	setHasPreview(false);
	    	previewResourceId = 0;
	    }
		
		/*
		 *  Check if refresh drawable is supplied.
		 *  If yes, get it's resource ID
		 */
		
		try {
	        refreshResourceId = getResources().getIdentifier("refresh", "drawable", getPackageName());
	    }

	    catch(Exception e) {
	    	refreshResourceId = 0;
	    }
	
		/*
		 *  Check if background drawable is supplied.
		 *  If yes, get it's resource ID
		 */
		
		try {
			backgroundResourceId = getResources().getIdentifier("background", "drawable", getPackageName());
	    }

	    catch(Exception e) {
	    	backgroundResourceId = 0;
	    }
		
		/*
		 *  Check if background extended drawable is supplied.
		 *  If yes, get it's resource ID
		 */
		
		try {
			backgroundExtendedResourceId = getResources().getIdentifier("background_extended", "drawable", getPackageName());
	    }

	    catch(Exception e) {
	    	backgroundExtendedResourceId = 0;
	    }
		// Assign the preview resouce or the preview not availiable resouce to the preview imageView
	    ImageView previewImage = (ImageView) findViewById(R.id.theme_preview);
		
	    if (hasPreview()) {
	    	previewImage.setImageResource(previewResourceId);
	    } else {
	    	previewImage.setImageResource(R.drawable.preview_not_available);
	    }
	    
	    // Setup the button listener
	    Button installTheme = (Button) findViewById(R.id.installTheme);
		
		installTheme.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//start the install theme method in the background thread
				new installTheme().execute(getPreviewResourceId(), getRefreshResourceId(), getBackgroundResourceId(), getBackgroundExtendedResourceId());
			}
			
		});
	
		// Setup the button listener
	    Button hideButton = (Button) findViewById(R.id.hideFromLauncher);
		
	    hideButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
		 
					// set title
					alertDialogBuilder.setTitle(R.string.hide_button);
		 
					// set dialog message
					alertDialogBuilder
						.setMessage(R.string.hide_warning)
						.setCancelable(false)
						.setPositiveButton(android.R.string.yes,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								//Hide the launcher from the drawer
								PackageManager p = getPackageManager();
								p.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
							
							}
						  })
						.setNegativeButton(android.R.string.no,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
					}
				});
				
	}
	
	private class installTheme extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
        	//Install the theme
        	ThemeInstaller.installTheme(getApplicationContext(), params[0], params[1], params[2], params[3]);
            
            return null;
        }        


		@Override
        protected void onPostExecute(String result) {
        	// Dismiss the progress dialog
        	progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
        	//Show progress dialog
        	progressDialog = ProgressDialog.show(MainActivity.this, getApplicationContext().getResources().getString(R.string.installing_title),
        			getApplicationContext().getResources().getString(R.string.installint_details), true, false);
           	
        }


		

	}


	/**
	 * @return the hasPreview
	 */
	public boolean hasPreview() {
		return hasPreview;
	}

	/**
	 * @param hasPreview the hasPreview to set
	 */
	public void setHasPreview(boolean hasPreview) {
		this.hasPreview = hasPreview;
	}

	

	/**
	 * @return the previewResourceId
	 */
	public int getPreviewResourceId() {
		return previewResourceId;
	}

	/**
	 * @param previewResourceId the previewResourceId to set
	 */
	public void setPreviewResourceId(int previewResourceId) {
		this.previewResourceId = previewResourceId;
	}

	/**
	 * @return the refreshResourceId
	 */
	public int getRefreshResourceId() {
		return refreshResourceId;
	}

	/**
	 * @param refreshResourceId the refreshResourceId to set
	 */
	public void setRefreshResourceId(int refreshResourceId) {
		this.refreshResourceId = refreshResourceId;
	}
	
	/**
	 * @return the backgroundResourceId
	 */
	public int getBackgroundResourceId() {
		return backgroundResourceId;
	}

	/**
	 * @param backgroundResourceId the backgroundResourceId to set
	 */
	public void setBackgroundResourceId(int backgroundResourceId) {
		this.backgroundResourceId = backgroundResourceId;
	}

	/**
	 * @return the backgroundExtendedResourceId
	 */
	public int getBackgroundExtendedResourceId() {
		return backgroundExtendedResourceId;
	}

	/**
	 * @param backgroundExtendedResourceId the backgroundExtendedResourceId to set
	 */
	public void setBackgroundExtendedResourceId(int backgroundExtendedResourceId) {
		this.backgroundExtendedResourceId = backgroundExtendedResourceId;
	}
}
