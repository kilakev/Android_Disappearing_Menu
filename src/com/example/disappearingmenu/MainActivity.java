
package com.example.disappearingmenu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {
	
	final Handler menu_handler = new Handler();
	boolean menu_visible = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.button1);
        btn.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
		
	}
    public boolean onTouchEvent(MotionEvent event) {
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    	if (!menu_visible)
    			displayMenu();
    	} else {
    		menu_handler.removeCallbacks(menu_hide_thread);
    	}
    	menu_handler.postDelayed(menu_hide_thread,5000);
    	return true;
    }
	private void hideMenu() {
    	menu_visible = false;
    	Animation a = AnimationUtils.loadAnimation(this, R.anim.hide_menu);
    	a.reset();
    	Button btn = (Button)findViewById(R.id.button1);
    	btn.startAnimation(a);
    	btn.setVisibility(View.INVISIBLE);
    }
	private void displayMenu() {
    	menu_visible = true;
    	Animation a = AnimationUtils.loadAnimation(this, R.anim.show_menu);
    	a.reset();
    	Button btn = (Button)findViewById(R.id.button1);
    	btn.clearAnimation();
    	btn.startAnimation(a);
    	btn.setVisibility(View.VISIBLE);
    }
	Runnable menu_hide_thread = new Runnable() {
    	public void run() {
    		hideMenu();
    	}
   
    };
}
