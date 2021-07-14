package gql.expressions.values;

import exceptions.SemanticErrorException;

import java.text.Normalizer;
import java.util.Objects;

public class GqlIdentifier extends Value{
    private GqlString id;

    public GqlIdentifier(String id) {
        if (id.equals("")) throw new SemanticErrorException("Id must be of size at least one.");
        this.id = new GqlString(Normalizer.normalize(id, Normalizer.Form.NFKC));
    }

    public String getId() {
        return id.gqlString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GqlIdentifier gqlIdentifier = (GqlIdentifier) o;
        return id.equals(gqlIdentifier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public TruthValue isEqualTo(Value valueToCompareTo) {
        if (valueToCompareTo instanceof GqlString) {
            return valueToCompareTo.isEqualTo(id);
        }

        return new TruthValue(this.equals(valueToCompareTo));
    }

    @Override
    public TruthValue isSmallerThan(Value valueToCompareTo) {
        return new TruthValue(null);
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return "nil";
        }

        return this.id.gqlString;
    }
}
