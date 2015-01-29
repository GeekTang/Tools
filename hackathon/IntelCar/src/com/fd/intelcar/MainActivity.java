package com.fd.intelcar;

import com.qualcomm.msdc.controller.IMSDCFileDeliveryControllerEventListener;
import com.qualcomm.msdc.controller.IMSDCStreamingControllerEventListener;
import com.qualcomm.msdc.object.FDFile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements IMSDCStreamingControllerEventListener, IMSDCFileDeliveryControllerEventListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void mpdUpdated(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetStreamingNotification() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceError(int arg0, String arg1, Integer arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceInitializeConfirmation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceListUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceStalled(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceStarted(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamingServiceStopped(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileAvailable(int arg0, FDFile arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileDeliveryServiceError(int arg0, String arg1, Integer arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileDeliveryServiceInitializeConfirmation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileDeliveryServiceListUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetFileDeliveryNotification() {
		// TODO Auto-generated method stub
		
	}
}
