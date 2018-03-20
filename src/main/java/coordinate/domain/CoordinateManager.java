package coordinate.domain;

import java.util.ArrayList;

import coordinate.view.PrintManager;

public class CoordinateManager {
	private ArrayList<PointLine> coordinateManager;

	public CoordinateManager(ArrayList<PointLine> coordinateManager) {
		this.coordinateManager = coordinateManager;
	}

	public static CoordinateManager init() {
		ArrayList<PointLine> coordinateManager = new ArrayList<>();
		for (int yAxis = 0; yAxis < PrintManager.MAXSIZE + 1; yAxis++) {
			PointLine pointLine = PointLine.init(yAxis);
			coordinateManager.add(pointLine);
		}
		return new CoordinateManager(coordinateManager);
	}

	public int size() {
		return coordinateManager.size();
	}

	public String getLabel(int i) {
		return coordinateManager.get(i).getLabel();
	}

}