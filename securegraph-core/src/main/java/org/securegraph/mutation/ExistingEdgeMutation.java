package org.securegraph.mutation;

import org.securegraph.Edge;

public abstract class ExistingEdgeMutation extends ExistingElementMutationImpl<Edge> implements EdgeMutation {
    private String newEdgeLabel;

    public ExistingEdgeMutation(Edge edge) {
        super(edge);
    }

    @Override
    public EdgeMutation alterEdgeLabel(String newEdgeLabel) {
        this.newEdgeLabel = newEdgeLabel;
        return this;
    }

    @Override
    public String getNewEdgeLabel() {
        return newEdgeLabel;
    }
}
