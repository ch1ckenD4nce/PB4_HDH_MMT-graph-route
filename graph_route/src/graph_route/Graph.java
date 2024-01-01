package graph_route;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Graph {
	ArrayList<Vertex> danhSachDinh;
	ArrayList<Edge> danhSachCanh;
	ArrayList<Edge> danhSachKq;
	ArrayList<ArrayList<Integer>> mtk;

	public Graph() {
		this.danhSachDinh = new ArrayList<>();
		this.danhSachCanh = new ArrayList<>();
		this.mtk = new ArrayList<>();
		this.danhSachKq = new ArrayList<>();
	}

	public ArrayList<Vertex> getDanhSachDinh() {
		return danhSachDinh;
	}

	public void setDanhSachDinh(ArrayList<Vertex> danhSachDinh) {
		this.danhSachDinh = danhSachDinh;
	}

	public ArrayList<Edge> getDanhSachCanh() {
		return danhSachCanh;
	}

	public void setDanhSachCanh(ArrayList<Edge> danhSachCanh) {
		this.danhSachCanh = danhSachCanh;
	}
	
	public ArrayList<Edge> getDanhSachKq() {
		return danhSachKq;
	}

	public void setDanhSachKq(ArrayList<Edge> danhSachKq) {
		this.danhSachKq = danhSachKq;
	}

	public ArrayList<ArrayList<Integer>> getMtk() {
		return mtk;
	}

	public void setMtk(ArrayList<ArrayList<Integer>> mtk) {
		this.mtk = mtk;
	}

	// them dinh
	public void themDinh(Ellipse2D el) {
		if (mtk.size() == 0) {
			mtk.add(new ArrayList<Integer>());
			mtk.get(0).add(0);
			danhSachDinh.add(new Vertex(mtk.size() - 1, mtk.size() - 1, new ArrayList<Vertex>(), el));
			return;
		}
		for(int i=0;i<mtk.size();i++)
			mtk.get(i).add(0);
		ArrayList<Integer> dongmoi= new ArrayList<Integer>();
		for (int i=0;i<mtk.size()+1;i++)
			dongmoi.add(0);
		mtk.add(dongmoi);
		
		danhSachDinh.add(new Vertex(mtk.size() - 1, mtk.size() - 1, new ArrayList<Vertex>(), el));
	}


	public boolean themCanhVoHuong(Vertex diemdau1, Vertex diemdau2, Line2D line2d, String weight) {
	    int cenX = (int) ((diemdau1.getEl().getCenterX() + diemdau2.getEl().getCenterX()) / 2);
	    int cenY = (int) ((diemdau1.getEl().getCenterY() + diemdau2.getEl().getCenterY()) / 2);

	    if (diemdau1.index < mtk.size() && diemdau2.index < mtk.size()) {
	        int weightInt = Integer.parseInt(weight);

	        // Kiểm tra cạnh đã tồn tại hay chưa
	        if (mtk.get(diemdau1.index).get(diemdau2.index) != 0
	                || mtk.get(diemdau2.index).get(diemdau1.index) != 0) {

	            // Cập nhật lại trọng số của cạnh
	            for (Edge edge : danhSachCanh) {
	                if ((edge.diemdau1 == diemdau1 && edge.diemdau2 == diemdau2)
	                        || (edge.diemdau1 == diemdau2 && edge.diemdau2 == diemdau1)) {
	                    edge.setWeight(weight);
	                }
	            }

	            // Cập nhật lại trọng số trong ma trận kề
	            mtk.get(diemdau1.index).set(diemdau2.index, weightInt);
	            mtk.get(diemdau2.index).set(diemdau1.index, weightInt);

	            return true;
	        } else {
	            // Nếu cạnh chưa tồn tại, thêm mới cạnh
	            mtk.get(diemdau1.index).set(diemdau2.index, weightInt);
	            mtk.get(diemdau2.index).set(diemdau1.index, weightInt);

	            danhSachCanh.add(new Edge(diemdau1, diemdau2, line2d, cenX, cenY, weight));
	            danhSachCanh.add(new Edge(diemdau2, diemdau1, line2d, cenX, cenY, weight));

	            diemdau1.danhSachKe.add(diemdau2);
	            diemdau2.danhSachKe.add(diemdau1);

	            return true;
	        }
	    } else {
	        return false;
	    }
	}
	
	

	public boolean themCanhKq(Vertex diemdau1, Vertex diemdau2, Line2D line2d, String weight) {
		int cenX = (int) ((diemdau1.getEl().getCenterX() + diemdau2.getEl().getCenterX()) /2);
		int cenY = (int) ((diemdau1.getEl().getCenterY() + diemdau2.getEl().getCenterY()) /2);
		
			danhSachKq.add(new Edge(diemdau1, diemdau2, line2d, cenX, cenY, weight));
			danhSachKq.add(new Edge(diemdau2, diemdau1, line2d, cenX, cenY, weight));
			
			return true;
		
	}

	
	public void resetEdgeKq(Line2D line2d, int index, int cenX, int cenY) {
		danhSachKq.get(index).setLine2D(line2d);
		danhSachKq.get(index).setCenterEdgeX(cenX);
		danhSachKq.get(index).setCenterEdgeY(cenY);
	}

	
	public void resetEl(Ellipse2D el, int index) {
		danhSachDinh.get(index).setEl(el);
		
	}
	
	public void resetEdge(Line2D line2d, int index, int cenX, int cenY) {
		danhSachCanh.get(index).setLine2D(line2d);
		danhSachCanh.get(index).setCenterEdgeX(cenX);
		danhSachCanh.get(index).setCenterEdgeY(cenY);
	}

	public Vertex isVertex(int nameOtherV) {
		Vertex oVertex= null;
		for(Vertex v: danhSachDinh) {
			if(v.ten == nameOtherV) oVertex =v;
		}
		return oVertex;
	}
}
