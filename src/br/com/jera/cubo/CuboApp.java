package br.com.jera.cubo;

import br.com.jera.graphic.GraphicDevice;
import br.com.jera.graphic.GraphicDevice.ALPHA_MODE;
import br.com.jera.graphic.Math.PRIMITIVE_TYPE;
import br.com.jera.graphic.Math.Vector2;
import br.com.jera.graphic.Math.Vector3;
import br.com.jera.graphic.Math.Vector4;
import br.com.jera.graphic.Math.Vertex;
import br.com.jera.graphic.Sprite;
import br.com.jera.graphic.Texture;
import br.com.jera.graphic.VertexArray;

public class CuboApp {

	private GraphicDevice graphicDevice;
	private VertexArray cube;
	private float angle = 0.0f;
	private Texture jeraTexture;
	private Sprite jeraSprite;

	public void draw() {

		angle += 1.0f;
		graphicDevice.beginScene();
		jeraTexture.bindTexture();

		graphicDevice.setup3DView();
		graphicDevice.setAlphaMode(ALPHA_MODE.NONE);
		cube.drawGeometry(new Vector3(0, 0, 5),
				new Vector3(0, angle, 0), new Vector3(1, 1, 1));
		jeraTexture.unbindTexture();

		graphicDevice.setup2DView();
		graphicDevice.setAlphaMode(ALPHA_MODE.DEFAULT);
		jeraSprite.draw(new Vector2(10,10).add(graphicDevice.getScreenSize().multiply(-0.5f)),
				jeraSprite.getBitmapSize(),
				0, new Vector2(0.0f, 0.0f), 0);

		graphicDevice.endScene();
	}

	public void create(GraphicDevice graphicDevice) {
		this.graphicDevice = graphicDevice;
		graphicDevice.setBackgroundColor(new Vector4(0.2f, 0.2f, 0.2f, 1.0f));
		graphicDevice.setCullingMode(GraphicDevice.CULLING_MODE.CULL_NONE);
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
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 0)),
			};

		cube = graphicDevice.createVertexArray(vertices, PRIMITIVE_TYPE.TRIANGLE_LIST);
		jeraTexture = graphicDevice.createStaticTexture(R.drawable.jera);
		jeraSprite = new Sprite(graphicDevice, R.drawable.logo_jera, 1, 1);
	}
}
