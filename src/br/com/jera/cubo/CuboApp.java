package br.com.jera.cubo;

import br.com.jera.graphic.GraphicDevice;
import br.com.jera.graphic.Math.PRIMITIVE_TYPE;
import br.com.jera.graphic.Math.Vector2;
import br.com.jera.graphic.Math.Vector3;
import br.com.jera.graphic.Math.Vector4;
import br.com.jera.graphic.Math.Vertex;
import br.com.jera.graphic.Texture;
import br.com.jera.graphic.VertexArray;

public class CuboApp {

	private GraphicDevice graphicDevice;
	private VertexArray cube;
	private float angle = 0.0f;
	private Texture jeraTexture;
	
	public void draw() {

		angle += 0.9f;
		graphicDevice.beginScene();
		jeraTexture.bindTexture();
		cube.drawGeometry(new Vector3(0, 0, -7),
				new Vector3(0, angle, 0), new Vector3(1, 1, 1));
		jeraTexture.unbindTexture();
		graphicDevice.endScene();
	}

	public void create(GraphicDevice graphicDevice) {
		this.graphicDevice = graphicDevice;
		graphicDevice.setBackgroundColor(new Vector4(0.2f, 0.2f, 0.2f, 1.0f));
		graphicDevice.setCullingMode(GraphicDevice.CULLING_MODE.CULL_NONE);
		Vertex[] vertices = {
				// face 1
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),
				new Vertex(new Vector3(-1, -1, -1), new Vector2(0, 1)),
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),

				new Vertex(new Vector3(1, 1, -1), new Vector2(1, 0)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 1)),

				// face 2
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, -1, 1), new Vector2(0, 1)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),

				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 0)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(1, 0)),
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),

				// face 3
				new Vertex(new Vector3(-1, -1, 1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, -1, -1), new Vector2(1, 0)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),

				new Vertex(new Vector3(-1, -1, 1), new Vector2(1, 1)),
				new Vertex(new Vector3(-1, 1, -1), new Vector2(0, 0)),
				new Vertex(new Vector3(-1, 1, 1), new Vector2(0, 1)),

				// face 4
				new Vertex(new Vector3(1, -1, -1), new Vector2(1, 0)),
				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),
				new Vertex(new Vector3(1, 1, -1), new Vector2(0, 0)),

				new Vertex(new Vector3(1, -1, 1), new Vector2(1, 1)),
				new Vertex(new Vector3(1, 1, 1), new Vector2(0, 1)),
				new Vertex(new Vector3(1, 1, -1), new Vector2(0, 0)),
			};

		cube = graphicDevice.createVertexArray(vertices, PRIMITIVE_TYPE.TRIANGLE_LIST);
		jeraTexture = graphicDevice.createStaticTexture(R.drawable.jera);
	}
}
