package com.altamiracorp.securegraph.query;

import com.altamiracorp.securegraph.Authorizations;
import com.altamiracorp.securegraph.Edge;
import com.altamiracorp.securegraph.Graph;
import com.altamiracorp.securegraph.Vertex;
import com.altamiracorp.securegraph.util.FilterIterable;

public class DefaultVertexQuery extends VertexQueryBase implements VertexQuery {
    public DefaultVertexQuery(Graph graph, Authorizations authorizations, Vertex sourceVertex) {
        super(graph, authorizations, sourceVertex);
    }

    @Override
    public Iterable<Edge> edges() {
        return new FilterIterable<Edge>(getGraph().getEdges(getParameters().getAuthorizations())) {
            @Override
            protected boolean isIncluded(Edge edge) {
                if (edge.getOutVertexId().equals(getSourceVertex().getId())) {
                    return true;
                }
                if (edge.getInVertexId().equals(getSourceVertex().getId())) {
                    return true;
                }
                return false;
            }
        };
    }
}
