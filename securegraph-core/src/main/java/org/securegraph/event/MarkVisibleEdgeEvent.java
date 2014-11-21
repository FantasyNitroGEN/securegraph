package org.securegraph.event;

import org.securegraph.Edge;
import org.securegraph.Graph;

public class MarkVisibleEdgeEvent extends GraphEvent {
    private final Edge edge;

    public MarkVisibleEdgeEvent(Graph graph, Edge edge) {
        super(graph);
        this.edge = edge;
    }

    public Edge getEdge() {
        return edge;
    }

    @Override
    public String toString() {
        return "MarkVisibleEdgeEvent{edge=" + edge + '}';
    }

    @Override
    public int hashCode() {
        return getEdge().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MarkVisibleEdgeEvent)) {
            return false;
        }

        MarkVisibleEdgeEvent other = (MarkVisibleEdgeEvent) obj;
        return getEdge().equals(other.getEdge()) && super.equals(obj);
    }
}
