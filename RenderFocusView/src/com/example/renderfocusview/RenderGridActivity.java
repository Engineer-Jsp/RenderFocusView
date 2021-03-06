package com.example.renderfocusview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RenderGridActivity extends Activity implements OnItemClickListener {

	private final static String[] data;

	static {
		data = new String[49];
		for (int i = 0; i < data.length; i++) {
			data[i] = "这是第  " + (i + 1) + " 项";
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.render_grid);
		GridView gridview = (GridView) findViewById(R.id.grid);
		gridview.setAdapter(new ArrayAdapter<String>(this,
				R.layout.render_item, android.R.id.text1, data));
		gridview.setOnItemClickListener(this);
		gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(RenderGridActivity.this,
						"long item: " + position, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "item click: " + position, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.render_grid, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.render_button) {
			startActivity(new Intent(this, RenderActivity.class));
			finish();
			return true;
		} else if (id == R.id.render_list) {
			startActivity(new Intent(this, RenderListActivity.class));
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}