package kr.or.opennet.android.yoongrab;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		Handler hd = new Handler();
		hd.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(MainActivity.this,
						PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

				finish();

			}
		}, 1200);
	}
}
