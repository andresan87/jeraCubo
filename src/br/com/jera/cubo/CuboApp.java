package br.com.jera.cubo;

import br.com.jera.graphic.GraphicDevice;
import br.com.jera.graphic.GraphicDevice.ALPHA_MODE;
import br.com.jera.graphic.Sprite;
import br.com.jera.graphic.Texture;
import br.com.jera.graphic.VertexArray;
import br.com.jera.input.InputListener;
import br.com.jera.util.BaseApplication;
import br.com.jera.util.FrameTimer;
import br.com.jera.util.Math.PRIMITIVE_TYPE;
import br.com.jera.util.Math.Vector2;
import br.com.jera.util.Math.Vector3;
import br.com.jera.util.Math.Vector4;
import br.com.jera.util.Math.Vertex;

public class CuboApp implements BaseApplication {

	private GraphicDevice graphicDevice;
	private VertexArray cube;
	private float angle = 0.0f;
	private Texture jeraTexture;
	private Sprite jeraSprite, headbenze, openglesLogo;
	private FrameTimer frameTimer = new FrameTimer();
	private InputListener input;
	private Vector2 glLogoPos = new Vector2();

	public void draw() {

		angle += 1.0f;

		Vector2 screenSize = graphicDevice.getScreenSize();

		graphicDevice.beginScene();
		jeraTexture.bindTexture();

		graphicDevice.setup3DView();
		graphicDevice.setAlphaMode(ALPHA_MODE.NONE);
		cube.drawGeometry(new Vector3(0, 0, 5), new Vector3(0, angle, 0),
				new Vector3(1, 1, 1));
		jeraTexture.unbindTexture();

		graphicDevice.setup2DView();
		graphicDevice.setAlphaMode(ALPHA_MODE.DEFAULT);

		openglesLogo.draw(glLogoPos, new Vector2(1,0));
		glLogoPos = glLogoPos.add(input.getTouchMove());

		jeraSprite.draw(new Vector2(10, 10), jeraSprite.getBitmapSize(), 0,
				new Vector2(0.0f, 0.0f), 0);

		headbenze.draw(new Vector2(screenSize.x / 2.0f, screenSize.y), 0,
				new Vector2(0.5f, 1.0f), frameTimer.setFrame(0, 3, 150));

		for (int t=0; t<input.getTouchCount(); t++) {
			if (input.getLastTouch(t) != null) {
				headbenze.draw(input.getLastTouch(t), 0, new Vector2(0.5f, 0.0f), 0);
			}
			if (input.getCurrentTouch(t) != null) {
				graphicDevice.setAlphaMode(ALPHA_MODE.ADD);
				headbenze.draw(input.getCurrentTouch(t), 0, new Vector2(0.5f, 0.0f), 15);
			}
		}

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
				new Vertex(new Vector3(-1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, -1, 1), new Vector2(1, 0)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),

				new Vertex(new Vector3(-1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 1)),

				// face 4
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 0)),
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 0)),

				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),
				new Vertex(new Vector3(1, 1, -1), new Vector2(0, 1)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 0)), };

		cube = graphicDevice.createVertexArray(vertices,
				PRIMITIVE_TYPE.TRIANGLE_LIST);
		jeraTexture = graphicDevice.createStaticTexture(R.drawable.jera);
		jeraSprite = new Sprite(graphicDevice, R.drawable.logo_jera, 1, 1);
		headbenze = new Sprite(graphicDevice, R.drawable.headbenze, 4, 4);
		openglesLogo = new Sprite(graphicDevice, R.drawable.opengles, 1, 1);
	}

	@Override
	public void resetFrameBuffer(int width, int height) {
		glLogoPos.x = (float)width;
	}

	@Override
	public void update() {
	}
}
