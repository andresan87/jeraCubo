package br.com.jera.cubo;

import br.com.jera.graphic.GraphicDevice;
import br.com.jera.graphic.GraphicDevice.ALPHA_MODE;
import br.com.jera.graphic.Sprite;
import br.com.jera.graphic.Texture;
import br.com.jera.graphic.VertexArray;
import br.com.jera.input.InputListener;
import br.com.jera.util.BaseApplication;
import br.com.jera.util.CommonMath.PRIMITIVE_TYPE;
import br.com.jera.util.CommonMath.Vector2;
import br.com.jera.util.CommonMath.Vector3;
import br.com.jera.util.CommonMath.Vector4;
import br.com.jera.util.CommonMath.Vertex;
import br.com.jera.util.FrameTimer;

public class CuboApp implements BaseApplication {

	private GraphicDevice graphicDevice;
	private VertexArray cube;
	private float angle = 0.0f;
	private Texture jeraTexture;
	private Sprite jeraSprite, headbenze, debugSurface;
	private FrameTimer frameTimer = new FrameTimer();
	private InputListener input;

	public void draw() {

		angle += 1.0f;

		Vector2 screenSize = graphicDevice.getScreenSize();

		graphicDevice.beginScene();

		graphicDevice.setup2DView();
		graphicDevice.setDepthTest(false);
		graphicDevice.setAlphaMode(ALPHA_MODE.DEFAULT);

		// tileMapPos = tileMapPos.add(input.getTouchMove());
		// tileMap.draw(new Classic2DViewer(tileMapPos.multiply(-1)));

		jeraSprite.draw(new Vector2(10, 10), jeraSprite.getBitmapSize(), 0, new Vector2(0.0f, 0.0f), 0, true);

		headbenze.draw(new Vector2(screenSize.x / 2.0f, screenSize.y), 0, new Vector2(0.5f, 1.0f), frameTimer.setFrame(0, 3, 150));

		graphicDevice.setDepthTest(true);
		graphicDevice.setup3DView();
		graphicDevice.setAlphaMode(ALPHA_MODE.NONE);
		jeraTexture.bindTexture();
		cube.drawGeometry(new Vector3(0, 0, 5), new Vector3(0, angle, 0), new Vector3(1, 1, 1));
		jeraTexture.unbindTexture();

		graphicDevice.setup2DView();
		graphicDevice.setDepthTest(false);
		graphicDevice.setAlphaMode(ALPHA_MODE.DEFAULT);
		for (int t = 0; t < input.getTouchCount(); t++) {
			if (input.getLastTouch(t) != null) {
				headbenze.draw(input.getLastTouch(t), 0, new Vector2(0.5f, 0.0f), 0);
			}
			if (input.getCurrentTouch(t) != null) {
				graphicDevice.setAlphaMode(ALPHA_MODE.ADD);
				headbenze.draw(input.getCurrentTouch(t), 0, new Vector2(0.5f, 0.0f), 15);
			}
		}

		debugSurface.draw(new Vector2(100, 100), new Vector2(0, 0));
		debugSurface.draw(new Vector2(100.325f, 140.325f), new Vector2(0, 0));
		debugSurface.draw(new Vector2(100.375f, 180.375f), new Vector2(0, 0));
		debugSurface.draw(new Vector2(100.5f, 220.5f), new Vector2(0, 0));

		graphicDevice.endScene();
	}

	public void create(GraphicDevice graphicDevice, InputListener listener) {
		this.graphicDevice = graphicDevice;
		this.input = listener;
		graphicDevice.setBackgroundColor(new Vector4(0.2f, 0.2f, 0.2f, 1.0f));
		graphicDevice.setCullingMode(GraphicDevice.CULLING_MODE.CULL_NONE);
	}

	@Override
	public void loadResources() {
		Vertex[] vertices = {
				// face 1
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),
				new Vertex(new Vector3(-1, -1, 1), new Vector2(0, 1)),
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),

				new Vertex(new Vector3(1, 1, 1), new Vector2(1, 0)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),

				// face 2
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, -1, -1), new Vector2(0, 1)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),

				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),
				new Vertex(new Vector3(1, 1, -1), new Vector2(1, 0)),
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),

				// face 3
				new Vertex(new Vector3(-1, -1, -1), new Vector2(1, 1)), new Vertex(new Vector3(-1, -1, 1), new Vector2(1, 0)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),

				new Vertex(new Vector3(-1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 1)),

				// face 4
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 0)), new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 0)),

				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)), new Vertex(new Vector3(1, 1, -1), new Vector2(0, 1)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 0)), };

		cube = graphicDevice.createVertexArray(vertices, PRIMITIVE_TYPE.TRIANGLE_LIST);
		jeraTexture = graphicDevice.createStaticTexture(R.drawable.jera);
		jeraSprite = new Sprite(graphicDevice, R.drawable.logo_jera, 1, 1);
		headbenze = new Sprite(graphicDevice, R.drawable.headbenze, 4, 4);
		debugSurface = new Sprite(graphicDevice, R.drawable.debug_surface, 1, 1);
	}

	@Override
	public void resetFrameBuffer(int width, int height) {
		graphicDevice.setup3DView(width, height);
	}

	@Override
	public STATE update(long lastFrameDeltaTimeMS) {
		return BaseApplication.STATE.CONTINUE;
	}

	@Override
	public String getStateName() {
		return "cubo";
	}
}
