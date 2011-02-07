package br.com.jera.cubo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import br.com.jera.gles1.GLESGraphicDevice;
import br.com.jera.graphic.GraphicDevice;
import br.com.jera.input.InputListener;

public class Renderer implements android.opengl.GLSurfaceView.Renderer {

	public Renderer(Context context, InputListener input) {
		this.input = input;
		this.context = context;
	}
	
	public void onDrawFrame(GL10 gl) {
		device.setTextureFilter(GraphicDevice.TEXTURE_FILTER.LINEAR);
		cubo.draw();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		device.setup3DView(width, height);
		cubo.resetSurface(width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		device = new GLESGraphicDevice(gl, context);
		cubo.create(device, input);
	}

	private Context context;
	private GraphicDevice device;
	private CuboApp cubo = new CuboApp();
	private InputListener input;
}
