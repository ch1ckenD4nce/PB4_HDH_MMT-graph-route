package graph_route;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	boolean startUnDir = false;
	boolean startDir = false;
	final int unit = 3;
	Graph graph;
	Vertex selected1, selected2;
	int clickX, clickY, pressX, pressY, dx, dy, midX, midY;
	String trongso = null;
	final int bankinh = 17;

	Line2D line2d;

	int vertexAmount;

	DrawPanel() {
		graph = new Graph();
		vertexAmount = graph.getMtk().size();

		// FOR PANEL
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(cursor);
		setBackground(Color.white);
		setPreferredSize(new Dimension(700, 3));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public int getVertexAmount() {
		return vertexAmount;
	}

	public void setVertexAmount(int vertexAmount) {
		this.vertexAmount = vertexAmount;
	}

	MouseAdapter mouse = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			for (int i = graph.danhSachDinh.size() - 1; i > -1; i--) {
				if (graph.danhSachDinh.get(i).el.contains(e.getX(), e.getY())) {
					pressX = e.getX();
					pressY = e.getY();
					return;
				}
			}
		}

		public void mouseDragged(MouseEvent e) {
			for (int i = graph.danhSachDinh.size() - 1; i > -1; i--) {
				dx = e.getX() - pressX;
				dy = e.getY() - pressY;
				Vertex v1 = graph.danhSachDinh.get(i);
				if (v1.el.contains(pressX, pressY)) {
					Ellipse2D v1Ellipse2d = new Ellipse2D.Double(v1.el.getX() + dx, v1.el.getY() + dy, 30, 30);
					graph.resetEl(v1Ellipse2d, i);
					for (int j = 0; j < graph.danhSachCanh.size(); j++) {
						Edge edge = graph.danhSachCanh.get(j);
						if (edge.diemdau1.el == v1.el) {
							line2d = new Line2D.Double(v1.el.getCenterX(), v1.el.getCenterY(),
									edge.diemdau2.el.getCenterX(), edge.diemdau2.el.getCenterY());
							midX = (int) ((v1Ellipse2d.getCenterX() + edge.diemdau2.el.getCenterX()) / 2 + 10);
							midY = (int) ((v1Ellipse2d.getCenterY() + edge.diemdau2.el.getCenterY()) / 2 + 10);

							graph.resetEdge(line2d, j, midX, midY);        

						} else if (edge.diemdau2.el == v1.el) {
							line2d = new Line2D.Double(edge.diemdau1.el.getCenterX(), edge.diemdau1.el.getCenterY(),
									v1.el.getCenterX(), v1.el.getCenterY());
							midX = (int) ((edge.diemdau1.el.getCenterX() + v1Ellipse2d.getCenterX()) / 2 + 10);
							midY = (int) ((edge.diemdau1.el.getCenterY() + v1Ellipse2d.getCenterY()) / 2 + 10);
							graph.resetEdge(line2d, j, midX, midY);

						}
					}
					
					
					for (int j = 0; j < graph.danhSachKq.size(); j++) {
						Edge edge = graph.danhSachKq.get(j);
						if (edge.diemdau1.el == v1.el) {
							line2d = new Line2D.Double(v1.el.getCenterX(), v1.el.getCenterY(),
									edge.diemdau2.el.getCenterX(), edge.diemdau2.el.getCenterY());
							midX = (int) ((v1Ellipse2d.getCenterX() + edge.diemdau2.el.getCenterX()) / 2 + 10);
							midY = (int) ((v1Ellipse2d.getCenterY() + edge.diemdau2.el.getCenterY()) / 2 + 10);

							graph.resetEdgeKq(line2d, j, midX, midY);        

						} else if (edge.diemdau2.el == v1.el) {
							line2d = new Line2D.Double(edge.diemdau1.el.getCenterX(), edge.diemdau1.el.getCenterY(),
									v1.el.getCenterX(), v1.el.getCenterY());
							midX = (int) ((edge.diemdau1.el.getCenterX() + v1Ellipse2d.getCenterX()) / 2 + 10);
							midY = (int) ((edge.diemdau1.el.getCenterY() + v1Ellipse2d.getCenterY()) / 2 + 10);
							graph.resetEdgeKq(line2d, j, midX, midY);

						}
					}
					
					pressX += dx;
					pressY += dy;

				}
				repaint();
			}

			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			clickX = e.getX();
			clickY = e.getY();

		
//			if (startUnDir) {

				for (int i = 0; i < graph.danhSachDinh.size(); i++) {
					if (graph.danhSachDinh.get(i).el.contains(clickX, clickY)) {

						if (selected1 == null) {
							selected1 = graph.danhSachDinh.get(i);
							return;
						} else {
							if (selected1 != null && selected1 != graph.danhSachDinh.get(i)) {
								
								selected2 = graph.danhSachDinh.get(i);
								
								
								trongso= GUI.trongField.getText().toString();
								if(Integer.parseInt(trongso) <= 0 || trongso == null) {
									JOptionPane.showMessageDialog(null, "Weight is invalid. Please re-enter.", "Error",
					                        JOptionPane.WARNING_MESSAGE);
									
								}else {
									line2d = new Line2D.Double(selected1.el.getCenterX(), selected1.el.getCenterY() ,
											selected2.el.getCenterX(), selected2.el.getCenterY() );
									graph.themCanhVoHuong(selected1, selected2, line2d, trongso);

									selected1 = null;
									selected2 = null;
									
								}

								
							}
							return;
						}
					}
					repaint();
				}
			
				Ellipse2D el = new Ellipse2D.Double(clickX, clickY, 30, 30);
				graph.themDinh(el);
				repaint();

//			}
		}
	};

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

			
		for (Edge edge : graph.danhSachCanh) {
			
			g2.setColor(new Color(0x397d5a));
			
			  float strokeWidth = 1.5f; 
                g2.setStroke(new BasicStroke(strokeWidth));

			

			g2.draw(edge.line2d);
			
			g2.setFont(new Font("Arial", Font.BOLD, 14));

			g2.setColor(Color.black);
	
			g2.drawString("" + edge.getWeight(), edge.getCenterEdgeX(), edge.getCenterEdgeY());
		
		}
			
			
			for (Edge edge : graph.danhSachKq) {
		
				g2.setColor(new Color(0xb70b0b));
				
				  float strokeWidth = 1.6f; // Điều chỉnh độ dày
	                g2.setStroke(new BasicStroke(strokeWidth));

				

				g2.draw(edge.line2d);
				
				

			}
			
			
			
			for (Vertex vertex : graph.danhSachDinh) {
				
				vertex.ten = vertex.index + 1;
				g2.setColor(new Color(0x397d5a));

				g2.fill(vertex.el);
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Arial", Font.BOLD, 14));
				g2.drawString("" + vertex.ten, (int) vertex.el.getCenterX() - unit,
						(int) vertex.el.getCenterY() + unit);

			}
			
			
			
			repaint();
			
		
	}

//	=========================== draw Edge
	public boolean drawEdge(int v1Name, int v2Name, String weight) {
		Vertex v1 = null;
		Vertex v2 = null;

		this.trongso = weight;

		if (v1Name != 0 && v2Name != 0 && weight != null) {

			for (Vertex v : graph.danhSachDinh) {
				if (v.isVertexName(v1Name)) {
					v1 = v;
				}
				if (v.isVertexName(v2Name)) {
					v2 = v;
				}
			}

			line2d = new Line2D.Double(v1.el.getCenterX(), v1.el.getCenterY() , v2.el.getCenterX(), v2.el.getCenterY());

			midX = (int) ((v1.el.getCenterX() + v2.el.getCenterX()) / 2 + 10);
			midY = (int) ((v1.el.getCenterY() + v2.el.getCenterY()) / 2 + 10);
		
			if (startUnDir) {
				graph.themCanhVoHuong(v1, v2, line2d, trongso);
				

				return true;
			}
		}
		return false;
	}
	//=================
	
	public boolean drawEdgeKq(int v1Name, int v2Name) {
		Vertex v1 = null;
		Vertex v2 = null;

		

		if (v1Name != 0 && v2Name != 0 ) {

			for (Vertex v : graph.danhSachDinh) {
				if (v.isVertexName(v1Name)) {
					v1 = v;
				}
				if (v.isVertexName(v2Name)) {
					v2 = v;
				}
			}

			line2d = new Line2D.Double(v1.el.getCenterX(), v1.el.getCenterY(), v2.el.getCenterX(), v2.el.getCenterY());

			midX = (int) ((v1.el.getCenterX() + v2.el.getCenterX()) / 2 + 10);
			midY = (int) ((v1.el.getCenterY() + v2.el.getCenterY()) / 2 + 10);
		
			
			graph.themCanhKq(v1, v2, line2d, "0");
			
		}
		return true;

		
	}




	
	// ================================= draw New page
	public void DrawNew() {
		graph.danhSachDinh.clear();
		graph.danhSachCanh.clear();
		graph.danhSachKq.clear();
		graph.getMtk().clear();
		startUnDir = true;
		repaint();
	}

	// ================================= draw delete Vertex and Edge
	public void deleteVertex(Vertex vertex) {
		int a = vertex.index;
		graph.danhSachDinh.remove(vertex);
		graph.getMtk().remove(a);
		for (int j = 0; j < graph.getMtk().size(); j++) {
			graph.getMtk().get(j).remove(a);
		}
		repaint();
		vertex.danhSachKe.clear();

		Iterator<Edge> iterator = graph.danhSachCanh.iterator();
		while (iterator.hasNext()) {
			Edge edge = iterator.next();
			if (edge.diemdau1.index == a || edge.diemdau2.index == a) {
				iterator.remove();
				repaint();
			}
		}
		for (int i = 0; i < graph.danhSachDinh.size(); i++) {
			graph.danhSachDinh.get(i).setIndex(i);
			repaint();
		}

		repaint();
	}

	// ================================= draw delete Edge
	
	public void deleteEdge(Vertex diem1, Vertex diem2) {
		int d1 = diem1.index;
		int d2 = diem2.index;
		diem1.danhSachKe.remove(diem2);
		diem2.danhSachKe.remove(diem1);
		Iterator<Edge> iterator = graph.danhSachCanh.iterator();
		while (iterator.hasNext()) {
			Edge edge = iterator.next();
			if (startUnDir) {
				if ((edge.diemdau1.index == d1 && edge.diemdau2.index == d2)
						|| (edge.diemdau2.index == d1 && edge.diemdau1.index == d2)) {
					graph.getMtk().get(d1).set(d2, 0);
					graph.getMtk().get(d2).set(d1, 0);
					iterator.remove();
					repaint();
				}

			}
			
		}
		repaint();
	}

	public String printARowOfMatrix() {
		String ans = "";
		vertexAmount = graph.getMtk().size();
		
		ArrayList<Edge> edgeList = graph.danhSachCanh;
		int graph[][] = new int[105][105];
		for (Edge e : edgeList) {
			int u = e.getDiemdau1().index + 1;
			int v = e.getDiemdau2().index + 1;
			int w = Integer.parseInt(e.getWeight());
			graph[u][v] = graph[v][u] = w;
		}
		int n = vertexAmount;
		ans += n + "\n";
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				ans += graph[i][j] + " ";
				
			}
			ans += "\n";
		}
		return ans;
	}

	
	public void drawGraphFromMatrix(int[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;
        Random random = new Random();
 
        // Xóa dữ liệu cũ
        graph.danhSachDinh.clear();
        graph.danhSachCanh.clear();
        graph.danhSachKq.clear();
        graph.getMtk().clear();
 
        // Tạo ngẫu nhiên toạ độ các đỉnh
        for (int i = 0; i < numVertices; i++) {
            int x = random.nextInt(getWidth() - 30);
            int y = random.nextInt(getHeight() - 30);
            Ellipse2D el = new Ellipse2D.Double(x, y, 30, 30);
            graph.themDinh(el);
        }
 
        // Thêm cạnh dựa trên ma trận kề
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                int weight = adjacencyMatrix[i][j];
                if (weight > 0) {
                    Vertex v1 = graph.danhSachDinh.get(i);
                    Vertex v2 = graph.danhSachDinh.get(j);
                     line2d = new Line2D.Double(v1.el.getCenterX(), v1.el.getCenterY(),
                            v2.el.getCenterX(), v2.el.getCenterY());
                     midX = (int) ((v1.el.getCenterX() + v2.el.getCenterX()) / 2 + 10);
                     midY = (int) ((v1.el.getCenterY() + v2.el.getCenterY()) / 2 + 10);
 
                    
                    
                    graph.themCanhVoHuong(v1, v2, line2d, Integer.toString(weight));
                    
//                    graph.resetEdge(line2d, graph.danhSachCanh.size() - 1, midX, midY);
                }
            }
        }
 

    }
	
	
	
	public void setStartUnDir(boolean startUnDir) {
		this.startUnDir = startUnDir;
	}

	public void setStartDir(boolean startDir) {
		this.startDir = startDir;
	}

	public String getTrongso() {
		return trongso;
	}

	public void setTrongso(String trongso) {
		this.trongso = trongso;
	}
}