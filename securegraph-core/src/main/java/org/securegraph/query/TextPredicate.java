package org.securegraph.query;

import org.securegraph.Property;
import org.securegraph.PropertyDefinition;
import org.securegraph.SecureGraphException;
import org.securegraph.TextIndexHint;
import org.securegraph.property.StreamingPropertyValue;
import org.securegraph.type.GeoPoint;

import java.util.Map;

public enum TextPredicate implements Predicate {
    CONTAINS;

    @Override
    public boolean evaluate(final Iterable<Property> properties, final Object second, Map<String, PropertyDefinition> propertyDefinitions) {
        for (Property property : properties) {
            PropertyDefinition propertyDefinition = propertyDefinitions.get(property.getName());
            if (evaluate(property, second, propertyDefinition)) {
                return true;
            }
        }
        return false;
    }

    private boolean evaluate(Property property, Object second, PropertyDefinition propertyDefinition) {
        Object first = property.getValue();
        if (!canEvaulate(first) || !canEvaulate(second)) {
            throw new SecureGraphException("Text predicates are only valid for string or GeoPoint fields");
        }

        String firstString = valueToString(first);
        String secondString = valueToString(second);

        switch (this) {
            case CONTAINS:
                if (propertyDefinition != null && !propertyDefinition.getTextIndexHints().contains(TextIndexHint.FULL_TEXT)) {
                    return false;
                }
                return firstString.contains(secondString);
            default:
                throw new IllegalArgumentException("Invalid text predicate: " + this);
        }
    }

    private String valueToString(Object val) {
        if (val instanceof GeoPoint) {
            val = ((GeoPoint) val).getDescription();
        } else if (val instanceof StreamingPropertyValue) {
            val = ((StreamingPropertyValue) val).readToString();
        }

        return ((String) val).toLowerCase();
    }

    private boolean canEvaulate(Object first) {
        if (first instanceof String) {
            return true;
        }
        if (first instanceof GeoPoint) {
            return true;
        }
        if (first instanceof StreamingPropertyValue && ((StreamingPropertyValue) first).getValueType() == String.class) {
            return true;
        }
        return false;
    }
}
