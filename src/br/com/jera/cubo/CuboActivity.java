package br.com.jera.cubo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class CuboActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		surface = new GLSurfaceView(this);
		surface.setRenderer(new Renderer(this));
		setContentView(surface);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private GLSurfaceView surface;
}