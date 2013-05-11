package com.example.com.opennet.safe;

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
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

			Toast.makeText(PageActivity.this, "Back키를 한 번 더 누르면 종료됩니다.",
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

						Toast.makeText(PageActivity.this, "보안 설정 화면으로 이동합니다. ",
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

				String HtmlString =

				"* 이 앱은 피싱앱의 위험을 경고하고, 금결원이 내세우는 금융앱스토어가 왜 소용이 없는지를 설명하기 위하여 오픈넷  <a href=\"http://opennet.or.kr\">(http://opennet.or.kr)</a> 이 배포한 앱입니다.";

				textView4.setText(Html.fromHtml(HtmlString));
				textView4.setMovementMethod(LinkMovementMethod.getInstance());

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
