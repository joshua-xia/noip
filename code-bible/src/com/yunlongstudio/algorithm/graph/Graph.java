package com.yunlongstudio.algorithm.graph;

public interface Graph {
  //mark
  public static interface Edge{
    public int getWeight();
  }
  
  /**
   * 其中的方法大多数比较一目了然，
   * 其中
   * 1）Edge firstEdge(int vertex)返回指定节点的边的链表里存的第一条边
   * 2）Edge nextEdge(Edge edge)，顺着边链表返回下一条边
   * 3）fromVertex,toVertex很简单返回该边的起始顶点和终结顶点
   * 4）getVertexLabel返回该定点对应的标号,assignLabels给所有顶点标上号
   * 
   * @return
   */

  int vertexesNum();
    
    int edgeNum();
    boolean isEdge(Edge edge);
    void setEdge(int from,int to, int weight);
    Edge firstEdge(int vertex);
    Edge nextEdge(Edge edge);
    int toVertex(Edge edge);
    int fromVertex(Edge edge);
    String getVertexLabel(int vertex);
    void assignLabels(String[] labels);
    void deepFirstTravel(GraphVisitor visitor);
    void breathFirstTravel(GraphVisitor visitor);
}
