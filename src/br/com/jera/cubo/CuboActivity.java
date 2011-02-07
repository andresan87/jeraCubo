package br.com.jera.cubo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import br.com.jera.platform.android.AndroidSurfaceView;

public class CuboActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		surface = new AndroidSurfaceView(this);
		surface.setRenderer(new Renderer(this, surface));
		surface.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		setContentView(surface);
	}

	@Override
	protected void onPause() {
		super.onPause();
		surface.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		surface.onResume();
	}

	private AndroidSurfaceView surface;
}