package com.yunlongstudio.algorithm.graph;

import java.util.Arrays;

public class DefaultGraph implements Graph {

    

    private static class _Edge implements Edge{
        
        
        private static final _Edge NullEdge=new _Edge();
        
        int from;
        int to;
        int weight;
        
        _Edge nextEdge;
        
        private _Edge()
        {
            weight=Integer.MAX_VALUE;
        }
        
        _Edge(int from, int to, int weight)
        {
            
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
        public int getWeight()
        {
            return weight;
        }
        
        
    }
    
    private static class _EdgeStaticQueue
    {
        _Edge first;
        _Edge last;
    }
    
    private int numVertexes;
    private String[] labels;
    private int numEdges;
    
    
    private _EdgeStaticQueue[] edgeQueues;
    
    //tag the specified vertex be visited or not
    private boolean[] visitTags;

    /**
     * 
     */
    public DefaultGraph(int numVertexes) {
        if(numVertexes<1)
        {
            throw new IllegalArgumentException();
        }
        this.numVertexes=numVertexes;
        this.visitTags=new boolean[numVertexes];
        this.labels=new String[numVertexes];
        for(int i=0;i<numVertexes;i++)
        {
            labels[i]=i+"";
            
            
        }
        this.edgeQueues=new _EdgeStaticQueue[numVertexes];
        for(int i=0;i<numVertexes;i++)
        {
            edgeQueues[i]=new _EdgeStaticQueue();
            edgeQueues[i].first=edgeQueues[i].last=_Edge.NullEdge;
            
        }
        this.numEdges=0;
    }

    

    /* (non-Javadoc)
     * @see algorithms.graph.Graph#edgeNum()
     */
    @Override
    public int edgeNum() {
        
        return numEdges;
    }

    /* (non-Javadoc)
     * @see algorithms.graph.Graph#firstEdge(int)
     */
    @Override
    public Edge firstEdge(int vertex) {
        if(vertex>=numVertexes)    throw new IllegalArgumentException();
        return edgeQueues[vertex].first;
        
    }

    /* (non-Javadoc)
     * @see algorithms.graph.Graph#isEdge(algorithms.graph.Graph.Edge)
     */
    @Override
    public boolean isEdge(Edge edge) {
        
        return (edge!=_Edge.NullEdge);
    }

    /* (non-Javadoc)
     * @see algorithms.graph.Graph#nextEdge(algorithms.graph.Graph.Edge)
     */
    @Override
    public Edge nextEdge(Edge edge) {
        
        return ((_Edge)edge).nextEdge;
    }

    /* (non-Javadoc)
     * @see algorithms.graph.Graph#vertexesNum()
     */
    @Override
    public int vertexesNum() {
        
        return numVertexes;
    }


    @Override
    public int fromVertex(Edge edge) {
        
        return ((_Edge)edge).from;
    }

    @Override
    public void setEdge(int from, int to, int weight) {
        //we don't allow ring exist 
        if(from<0||from>=numVertexes||to<0||to>=numVertexes||weight<0||from==to)throw new IllegalArgumentException();
        _Edge edge=new _Edge(from,to,weight);
        edge.nextEdge=_Edge.NullEdge;
        if(edgeQueues[from].first==_Edge.NullEdge)
            edgeQueues[from].first=edge;
        else 
            edgeQueues[from].last.nextEdge=edge;
        edgeQueues[from].last=edge;
        
    }

    @Override
    public int toVertex(Edge edge) {

        return ((_Edge)edge).to;
    }

    @Override
    public String getVertexLabel(int vertex) {
        return labels[vertex];
    }

    @Override
    public void assignLabels(String[] labels) {
        System.arraycopy(labels, 0, this.labels, 0, labels.length);
        
    }



    @Override
    public void deepFirstTravel(GraphVisitor visitor) {
        Arrays.fill(visitTags, false);//reset all visit tags
        for(int i=0;i<numVertexes;i++)
        {
            if(!visitTags[i])do_DFS(i,visitor);
        }

    }
    

    private final void do_DFS(int v, GraphVisitor visitor) {
        //first visit this vertex
        visitor.visit(this, v);
        visitTags[v]=true;
        
        //for each edge from this vertex, we do one time
        //and this for loop is very classical in all graph algorithms
        for(Edge e=firstEdge(v);isEdge(e);e=nextEdge(e))
        {
            if(!visitTags[toVertex(e)])
            {
                do_DFS(toVertex(e),visitor);
            }
        }
    }



    private static class _IntQueue
    {
        private static class _IntQueueNode
        {
            _IntQueueNode next;
            int value;
        }
        _IntQueueNode first;
        _IntQueueNode last;
        
        //queue can only insert to the tail
        void add(int i)
        {
            _IntQueueNode node=new _IntQueueNode();
            node.value=i;
            node.next=null;
            if(first==null)first=node;
            else last.next=node;
            last=node;
        }
        
        
        boolean isEmpty()
        {
            return first==null;
        }
        //queue can only remove from the head
        int remove()
        {
            int val=first.value;
            if(first==last)
                first=last=null;
            else
                first=first.next;
            return val;
        }
        
    }
    /* (non-Javadoc)
     * @see algorithms.graph.Graph#breathFirstTravel(algorithms.graph.GraphVisitor)
     */
    @Override
    public void breathFirstTravel(GraphVisitor visitor) {
        Arrays.fill(visitTags, false);//reset all visit tags
        
        
        for(int i=0;i<numVertexes;i++)
        {
            if(!visitTags[i])
            {
                    
                do_BFS(i,visitor);
            }
        }

    }

    private void do_BFS(int v, GraphVisitor visitor) {
        //and BFS will use an queue to keep the unvisited vertexes
        // we  can also just use java.util.ArrayList
        _IntQueue queue=new _IntQueue();
        queue.add(v);
        while(!queue.isEmpty())
        {
            int fromV=queue.remove();
            visitor.visit(this, fromV);
            visitTags[fromV]=true;
            for(Edge e=firstEdge(fromV);isEdge(e);e=nextEdge(e))
            {
                if(!visitTags[toVertex(e)])
                {
                    queue.add(toVertex(e));
                }
            }
        }
    }

       //to be continue

}
