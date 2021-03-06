package com.example.renderfocusview;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RenderActivity extends Activity implements View.OnClickListener,
		View.OnLongClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.render_demo);
		initView();
	}

	private void initView() {
		View view = findViewById(R.id.parent_view);
		if (view instanceof LinearLayout) {
			LinearLayout mLinearLayout = (LinearLayout) view;
			int count = mLinearLayout.getChildCount();
			for (int i = 0; i < count; i++) {
				View child = mLinearLayout.getChildAt(i);
				if (child instanceof RenderFocusView) {
					RenderFocusView mRenderFocusView = (RenderFocusView) child;
					mRenderFocusView.setRenderColor(0xff000000 | new Random()
							.nextInt(0x00ffffff));
					mRenderFocusView
							.setBackgroundColor(0xff000000 | new Random()
									.nextInt(0x00ffffff));
					int index = mRenderFocusView.getChildCount();
					for (int j = 0; j < index; j++) {
						View button = mRenderFocusView.getChildAt(j);
						if (button instanceof Button) {
							((Button) button).setText("我是xml添加的  " + (i + 1));
							button.setOnLongClickListener(this);
							button.setOnClickListener(this);
						}
					}
				} else if (child instanceof Button) {
					((Button) child).setText("我是代码添加的");
					((Button) child).setTextColor(Color.WHITE);
					RenderFocusView
							.on(child)
							.RenderColor(
									0xff000000 | new Random()
											.nextInt(0x00ffffff))// Color.parseColor("#0000FF")
							.RenderAlpha(0.2f).RenderHover(true).create();
					child.setOnLongClickListener(this);
					child.setOnClickListener(this);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "short click", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onLongClick(View v) {
		if (v.getId() == R.id.ripple_layout_1) {
			Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show();
			return false;
		}
		Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.render_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.render_list) {
			startActivity(new Intent(this, RenderListActivity.class));
			finish();
			return true;
		} else if (id == R.id.render_grid) {
			startActivity(new Intent(this, RenderGridActivity.class));
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
