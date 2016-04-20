package com.example.airpureanim2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity
{
	Timer timer;
	ImageView mouth, mouthBig;
	BlackHoleView mBlackHoleView;
	int time;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mouth = (ImageView) findViewById(R.id.circle_image);
		mouth.setAnimation(AnimationUtils.loadAnimation(this, R.anim.mouthanim));

		mouthBig = (ImageView) findViewById(R.id.circle_image_big);
		mouthBig.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.mouth_biganim));
		
		mBlackHoleView = (BlackHoleView) findViewById(R.id.main_blackhole_view);
		
		timer = new Timer();
		timer.schedule(new HoleTask(), 0, 20);
		
	}
	
	class HoleTask extends TimerTask
	{
		@Override
		public void run()
		{
			time = time + 20;
			handler.sendEmptyMessage(0);
		}
	}

	Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			if (time % 3000 == 0)
			{
				mBlackHoleView.setInvalidate(true);
				mBlackHoleView.invalidate();
			}
			else
			{
				mBlackHoleView.setInvalidate(false);
				mBlackHoleView.invalidate();
			}
		}
	};
}
