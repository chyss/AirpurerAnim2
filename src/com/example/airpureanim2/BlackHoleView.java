package com.example.airpureanim2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BlackHoleView extends View
{
	int widthTop, widthBottom;
	List<IonPosition> positions = new ArrayList<IonPosition>();
	
	List<IonPosition> positionsBase = new ArrayList<IonPosition>();
	
	int w,h;

	public BlackHoleView(Context context)
	{
		super(context);
	}

	public BlackHoleView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	/**
	 * 
	 * @param reSet : if true initPosition,else invalidate
	 */
	public void setInvalidate(boolean reSet)
	{
		w = getWidth();
		h = getHeight();
		if (reSet)
		{
			initPosition(w,h);
		}
		else
		{
			changePosition(w,h);
		}
	}

	private void changePosition(int w, int h) 
	{
		for (int i = 0; i < positions.size(); i++)
		{
			int x0 = positionsBase.get(i).getxPosition();
			int y0 = positionsBase.get(i).getyPosition();
			
			int x = positions.get(i).getxPosition();
			int y = positions.get(i).getyPosition();
			
			int distance = (int) Math.sqrt((((w / 2) - x) * ((w / 2) - x))+(((h / 2) - y) * ((h / 2) - y)));
			
			if (distance < 350)
			{
				positions.get(i).setxPosition(x0 + (w / 2 - positionsBase.get(i).getxPosition()) / 20);
				positions.get(i).setyPosition(y0 + (h / 2 - positionsBase.get(i).getyPosition()) / 20);
			}
			else
			{
				positions.get(i).setxPosition(x0 + (w / 2 - positionsBase.get(i).getxPosition()) / 100);
				positions.get(i).setyPosition(y0 + (h / 2 - positionsBase.get(i).getyPosition()) / 100);
			}
		}
	}

	private void initPosition(int w, int h)
	{
		positions.clear();
		positionsBase.clear();
		
		for (int i = 0; i < 10; i++)
		{
			IonPosition ionPosition = new IonPosition();
			ionPosition.setxPosition((int)(Math.random() * w));
			ionPosition.setyPosition((int)(Math.random() * (-100)));
			ionPosition.setRadius((int)(Math.random() * 10));
			positions.add(ionPosition);
		}
		
		for (int i = 0; i < 10; i++)
		{
			IonPosition ionPosition = new IonPosition();
			ionPosition.setxPosition((int)(Math.random() * w));
			ionPosition.setyPosition(h + (int)(Math.random() * (100)));
			ionPosition.setRadius((int)(Math.random() * 10));
			positions.add(ionPosition);
		}
		
		for (int i = 0; i < 10; i++)
		{
			IonPosition ionPosition = new IonPosition();
			ionPosition.setxPosition((int)(Math.random() * (-100)));
			ionPosition.setyPosition((int)(Math.random() * h));
			ionPosition.setRadius((int)(Math.random() * 10));
			positions.add(ionPosition);
		}
		
		for (int i = 0; i < 10; i++)
		{
			IonPosition ionPosition = new IonPosition();
			ionPosition.setxPosition(w + (int)(Math.random() * (100)));
			ionPosition.setyPosition((int)(Math.random() * h));
			ionPosition.setRadius((int)(Math.random() * 10));
			positions.add(ionPosition);
		}
		
		positionsBase = positions;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint mPaintNote = new Paint();
		mPaintNote.setStrokeWidth(20);
		
		for (int i = 0; i < positions.size(); i++)
		{
			int x = positions.get(i).getxPosition();
			int y = positions.get(i).getyPosition();
			
			int distance = (int) Math.sqrt((((w / 2) - x) * ((w / 2) - x))+(((h / 2) - y) * ((h / 2) - y)));
			
			if (distance > 100) 
			{
				mPaintNote.setColor(Color.parseColor("#ffffff"));
				canvas.drawCircle(positions.get(i).getxPosition(), positions.get(i).getyPosition(), positions.get(i).getRadius(), mPaintNote);
			}
		}
	}
}
