package br.com.jera.cubo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import br.com.jera.gles1.GLESGraphicDevice;
import br.com.jera.graphic.GraphicDevice;

public class Renderer implements android.opengl.GLSurfaceView.Renderer {

	public Renderer(Context context) {
		this.context = context;
	}
	
	public void onDrawFrame(GL10 gl) {
		device.setTextureFilter(GraphicDevice.TEXTURE_FILTER.LINEAR);
		cubo.draw();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		device.setup3DView(width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		device = new GLESGraphicDevice(gl, context);
		cubo.create(device);
	}

	Context context;
	GraphicDevice device;
	CuboApp cubo = new CuboApp();
}
