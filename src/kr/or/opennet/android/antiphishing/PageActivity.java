package kr.or.opennet.android.antiphishing;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import kr.or.opennet.android.antiphishing.R;

public class PageActivity extends Activity {

	LayoutInflater inflate;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);

		inflate = getLayoutInflater();
		viewPager = (ViewPager) findViewById(R.id.IntroPage);

		IntroAdapter adapter = new IntroAdapter();
		viewPager.setAdapter(adapter);

	}

	boolean backKey = false;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (backKey) {
			finish();

		} else {

			Toast.makeText(PageActivity.this, getString(R.string.exit),
					Toast.LENGTH_SHORT).show();

			backKey = true;

			Handler hd = new Handler();
			hd.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					backKey = false;
				}
			}, 2000);

		}

	}

	public class IntroAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			View view = (View) arg2;
			((ViewPager) arg0).removeView((View) view);
			view = null;
		}

		@Override
		public boolean isViewFromObject(View pager, Object obj) {
			// TODO Auto-generated method stub
			return pager == obj;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;// jsonarray.length();
		}

		@Override
		public void notifyDataSetChanged() {
			// TODO Auto-generated method stub
			super.notifyDataSetChanged();
		}

		@Override
		public Object instantiateItem(View pager, int position) {
			// TODO Auto-generated method stub
			View v = null;

			if (position == 0) {
				v = inflate.inflate(R.layout.page1, null);
			} else if (position == 1) {
				v = inflate.inflate(R.layout.page2, null);
			} else if (position == 2) {
				v = inflate.inflate(R.layout.page3, null);

				TextView textView4 = (TextView) v.findViewById(R.id.textView4);
				ImageView Button = (ImageView) v.findViewById(R.id.button1);

				Button.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Toast.makeText(PageActivity.this, getString(R.string.move_to_settings),
								Toast.LENGTH_SHORT).show();

                        final String targetSetting;
                        if (Build.VERSION.SDK_INT <= 10) {
                            targetSetting = Settings.ACTION_APPLICATION_SETTINGS;
                        } else {
                            targetSetting = Settings.ACTION_SECURITY_SETTINGS;
                        }

                        Intent intent = new Intent(targetSetting);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

						startActivity(intent);

					}
				});

			}

			((ViewPager) pager).addView(v);
			return v;

		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View view) {
		}

		@Override
		public void finishUpdate(View arg0) {
		}
	}

}
